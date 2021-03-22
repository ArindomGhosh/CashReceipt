package com.arindom.cashrecipt.views.screens.detailstab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.FragmentDetailsTabletBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.widgets.receiptdetails.ReceiptDetailsWidget
import com.arindom.cashrecipt.views.widgets.receiptlist.ReceiptListWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashDetailsTabletScreen : Fragment() {
    private lateinit var mBinding: FragmentDetailsTabletBinding
    private val mCashReceiptTabletViewModel: CashReceiptTabletViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details_tablet, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSubscription()
        inflateCashReceiptListWidget()
    }

    private fun addSubscription() {
        mCashReceiptTabletViewModel.cashReceiptLiveData.observe(viewLifecycleOwner) {
            inflateCashReceiptDetailsWidget(it)
        }
    }

    private fun inflateCashReceiptDetailsWidget(cashReceipt: CashReceipt) {
        childFragmentManager.beginTransaction()
            .replace(mBinding.frContainerDetails.id,
                ReceiptDetailsWidget(cashReceipt = cashReceipt,
                    onProductSelected = { itemWithPrice ->
                        Toast.makeText(
                            requireContext(),
                            "You paid Rs.${itemWithPrice.productPrice} for ${itemWithPrice.productName}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }))
            .commit()
    }

    private fun inflateCashReceiptListWidget() {
        childFragmentManager.beginTransaction()
            .replace(
                mBinding.frContainerList.id,
                ReceiptListWidget(
                    action = { cashReceipt ->
                        mCashReceiptTabletViewModel.cashReceiptLiveData.postValue(cashReceipt)
                    },
                    showCategory = true // default true
                )
            )
            .commit()
    }
}