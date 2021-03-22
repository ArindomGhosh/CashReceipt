package com.arindom.cashrecipt.views.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.arindom.cashrecipt.CashReceiptApplication
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.FragmentCashreceiptListBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.Listener
import com.arindom.cashrecipt.views.widgets.receiptlist.ReceiptListWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashReceiptListScreen : Fragment() {
    private lateinit var mBinding: FragmentCashreceiptListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cashreceipt_list, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .replace(
                mBinding.frContainerReceiptList.id,
                ReceiptListWidget(
                    action = { cashReceipt ->
                        val action =
                            CashReceiptListScreenDirections.actionCashReceiptListScreenToReceiptDetailsScreen(
                                cashReceipt.receiptId
                            )
                        mBinding.root.findNavController().navigate(action)
                    },
                    showCategory = true // default true
                )
            )
            .commit()
    }
}