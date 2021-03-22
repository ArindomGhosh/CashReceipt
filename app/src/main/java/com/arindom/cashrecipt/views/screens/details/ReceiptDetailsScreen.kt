package com.arindom.cashrecipt.views.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.FragmentCashreceiptDetailsBinding
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.*
import com.arindom.cashrecipt.views.widgets.receiptdetails.ReceiptDetailsWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiptDetailsScreen :
    Fragment() {
    private lateinit var mBinding: FragmentCashreceiptDetailsBinding
    private val mReceiptDetailsScreenViewModel: ReceiptDetailsScreenViewModel by viewModels()

    private val cashReceiptObserver: Observer<UIState<CashReceipt>> = Observer {
        mReceiptDetailsScreenViewModel.notifyChange()
        updateUI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cashreceipt_details,
            container,
            false
        )
        mBinding.receiptDetailsVM = mReceiptDetailsScreenViewModel
        addSubscription()
        return mBinding.root
    }

    private fun addSubscription() {
        mReceiptDetailsScreenViewModel.getCashReceiptLiveDate()
            .observe(
                viewLifecycleOwner,
                cashReceiptObserver
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            mReceiptDetailsScreenViewModel.fetchReceiptDetails(
                ReceiptDetailsScreenArgs.fromBundle(
                    bundle
                ).receipId
            )
        }
    }

    private fun updateUI(uiState: UIState<CashReceipt>) {
        uiState.data?.let {
            childFragmentManager.beginTransaction()
                .replace(mBinding.frContainerReceiptDetails.id,
                    ReceiptDetailsWidget(cashReceipt = it,
                        onProductSelected = { itemWithPrice ->
                            Toast.makeText(
                                requireContext(),
                                "You paid Rs.${itemWithPrice.productPrice} for ${itemWithPrice.productName}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }))
                .commit()
        }
    }
}