package com.arindom.cashrecipt.views.widgets.receiptdetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.WidgetReceiptDetailsBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.network.responses.ReceiptDetailsLayout
import com.arindom.cashrecipt.views.UIState
import com.arindom.cashrecipt.views.util.ReceiptDetailsChildren
import com.arindom.cashrecipt.views.widgets.productinfo.ProductInfoWidget
import com.arindom.cashrecipt.views.widgets.recieptsummary.ReceiptSummary
import com.arindom.cashrecipt.views.widgets.recieptsummary.ReceiptSummaryWidget
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetails
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetailsWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun ReceiptDetailsWidget(
    cashReceipt: CashReceipt,
    onProductSelected: ((CashReceipt.ItemWithPrice) -> Unit)? = null,
): ReceiptDetailsFragment {
    return ReceiptDetailsFragment(
        cashReceipt = cashReceipt,
        onProductSelected = onProductSelected
    )
}

@AndroidEntryPoint
class ReceiptDetailsFragment(
    private val cashReceipt: CashReceipt,
    private val onProductSelected: ((CashReceipt.ItemWithPrice) -> Unit)? = null,
) : Fragment() {
    private lateinit var mBinding: WidgetReceiptDetailsBinding
    private val children = mutableListOf<ReceiptDetailsChildren>()
    private val mFragmentList = mutableListOf<Fragment>()
    private val mReceiptDetailsWidgetViewModel: ReceiptDetailsWidgetViewModel by viewModels()
    private val layoutLiveDataObserver: Observer<UIState<ReceiptDetailsLayout>> =
        Observer { receiptDetailsLayout ->
            receiptDetailsLayout.data
                ?.let { receiptLayout ->
                    receiptLayout.rows
                        .forEach { child ->
                            this.children.add(ReceiptDetailsChildren.valueOf(child))
                        }
                    updateUI(cashReceipt = cashReceipt)
                }
            mReceiptDetailsWidgetViewModel.notifyChange()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.widget_receipt_details, container, false)
        addSubscriptions()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.receiptDetailsVM = mReceiptDetailsWidgetViewModel
        mReceiptDetailsWidgetViewModel.onReceiptEventTrigger(ReceiptDetailsWidgetEvent.FetchLayoutDetailsEvent)
    }

    private fun addSubscriptions() {
        mReceiptDetailsWidgetViewModel.getLayoutLiveDate()
            .observe(viewLifecycleOwner, layoutLiveDataObserver)
    }

    private fun updateUI(cashReceipt: CashReceipt) {
        lifecycleScope.launch(Dispatchers.Main) {
            children.asFlow()
                .map { child ->
                    when (child) {
                        ReceiptDetailsChildren.UserInfo -> UserDetailsWidget(
                            userDetails = UserDetails(cashReceipt)
                        )
                        ReceiptDetailsChildren.ProductInfo -> ProductInfoWidget(
                            mItemWithPriceList = cashReceipt.itemListWithPrice,
                            onProductSelectedListener = { itemWithPrice ->
                                onProductSelected?.invoke(itemWithPrice)
                            }
                        )
                        ReceiptDetailsChildren.ReceiptInfo -> ReceiptSummaryWidget(
                            receiptModel = ReceiptSummary(cashReceipt)
                        )
                    }
                }.collect { fragment ->
                    mFragmentList.add(fragment)
                }
            beginFragmentTransactions()
        }
    }

    private fun beginFragmentTransactions() {
        val fragmentContainer = mBinding.llFragmentContainer
        fragmentContainer.removeAllViews()
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )
        mFragmentList.forEachIndexed { index, fragment ->
            val mFragmentContainerView = FragmentContainerView(requireContext())
            mFragmentContainerView.layoutParams = layoutParams
            mFragmentContainerView.id = index + 1
            mFragmentContainerView.setBackgroundColor(Color.TRANSPARENT)
            fragmentContainer.addView(mFragmentContainerView)
            childFragmentManager.beginTransaction()
                .replace(mFragmentContainerView.id, fragment)
                .commit()
        }
    }
}