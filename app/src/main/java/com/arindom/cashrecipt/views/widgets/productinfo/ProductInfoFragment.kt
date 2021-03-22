package com.arindom.cashrecipt.views.widgets.productinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.WidgetProductInfoBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import dagger.hilt.android.AndroidEntryPoint

fun ProductInfoWidget(
    mItemWithPriceList: List<CashReceipt.ItemWithPrice>,
    onProductSelectedListener: ((CashReceipt.ItemWithPrice) -> Unit)? = null
): ProductInfoFragment {
    return ProductInfoFragment(
        mItemWithPriceList = mItemWithPriceList,
        onProductSelectedListener = onProductSelectedListener
    )
}

@AndroidEntryPoint
class ProductInfoFragment(
    private val mItemWithPriceList: List<CashReceipt.ItemWithPrice>,
    private val onProductSelectedListener: ((CashReceipt.ItemWithPrice) -> Unit)? = null
) :
    Fragment() {
    private val mProductInfoViewModel: ProductInfoViewModel by viewModels()
    private lateinit var mBinding: WidgetProductInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.widget_product_info, container, false)
        mProductInfoViewModel.setItemPriceList(mItemWithPriceList)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProductInfoViewModel.productDetailListLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(productList: List<CashReceipt.ItemWithPrice>) {
        val adapter = ProductListAdapter(
            mContext = requireContext(),
            productList = productList,
            onProductSelectedListener = onProductSelectedListener
        )
        val mLinearLayoutManager = LinearLayoutManager(requireContext())
        mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mBinding.rvProductInfo.layoutManager = mLinearLayoutManager
        mBinding.rvProductInfo.adapter = adapter
    }
}