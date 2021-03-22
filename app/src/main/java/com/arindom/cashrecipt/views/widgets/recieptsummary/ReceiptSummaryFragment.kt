package com.arindom.cashrecipt.views.widgets.recieptsummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.databinding.WidgetReceiptSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

fun ReceiptSummaryWidget(
    receiptModel: ReceiptSummary
): ReceiptSummaryFragment {
    return ReceiptSummaryFragment(receiptModel = receiptModel)
}

@AndroidEntryPoint
class ReceiptSummaryFragment(private val receiptModel: ReceiptSummary) :
    Fragment() {
    private val mReceiptSummaryViewModel: ReceiptSummaryViewModel by viewModels()
    private lateinit var mBinding: WidgetReceiptSummaryBinding
    private val receiptSummaryObserver: Observer<ReceiptSummary> = Observer {
        mReceiptSummaryViewModel.notifyChange()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.widget_receipt_summary, container, false)
        mBinding.receiptSummaryVM = mReceiptSummaryViewModel
        mReceiptSummaryViewModel.setReceiptSummary(receiptModel)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mReceiptSummaryViewModel.receiptSummaryLiveData.observe(
            viewLifecycleOwner,
            receiptSummaryObserver
        )
    }
}