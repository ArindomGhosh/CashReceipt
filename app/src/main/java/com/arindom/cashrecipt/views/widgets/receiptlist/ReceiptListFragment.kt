package com.arindom.cashrecipt.views.widgets.receiptlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.WidgetReceiptListBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.UIState
import dagger.hilt.android.AndroidEntryPoint

fun ReceiptListWidget(
    action: ((CashReceipt) -> Unit)? = null,
    showCategory: Boolean = true
): ReceiptListFragment {
    return ReceiptListFragment(
        action = action,
        showCategory = showCategory
    )
}

@AndroidEntryPoint
class ReceiptListFragment(
    private val action: ((CashReceipt) -> Unit)? = null,
    private val showCategory: Boolean = true
) : Fragment() {
    private val mReceiptListViewModel: ReceiptListViewModel by viewModels()
    private lateinit var mBinding: WidgetReceiptListBinding
    private val cashReceiptObserver: Observer<UIState<List<CashReceipt>>> = Observer {
        mReceiptListViewModel.notifyChange()
        updateUI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.widget_receipt_list, container, false)
        with(mReceiptListViewModel) {
            showCategory = this@ReceiptListFragment.showCategory
        }
        addSubscriptions()
        return mBinding.root
    }

    private fun addSubscriptions() {
        mReceiptListViewModel.getCashReceiptLiveDate()
            .observe(viewLifecycleOwner, cashReceiptObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.receiptListVM = mReceiptListViewModel
        mReceiptListViewModel.onReceiptListEventTriggered(ReceiptListEvent.FetchReceiptListEvent)
    }

    private fun updateUI(uiState: UIState<List<CashReceipt>>) {
        uiState.data?.let {
            val receiptListAdapter =
                ReceiptListAdapter(it, action, mReceiptListViewModel.showCategory)
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            mBinding.rvReceiptList.layoutManager = layoutManager
            mBinding.rvReceiptList.adapter = receiptListAdapter
        }
    }
}