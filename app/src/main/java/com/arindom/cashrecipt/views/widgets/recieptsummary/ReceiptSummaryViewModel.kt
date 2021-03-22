package com.arindom.cashrecipt.views.widgets.recieptsummary

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arindom.cashrecipt.views.CashReceiptViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReceiptSummaryViewModel @Inject constructor() : CashReceiptViewModel() {
    val receiptSummaryLiveData = MutableLiveData<ReceiptSummary>()

    fun setReceiptSummary(receiptSummary: ReceiptSummary) {
        receiptSummaryLiveData.postValue(receiptSummary)
    }

    @Bindable
    fun getReceiptId(): String {
        return receiptSummaryLiveData.value?.let { "Receipt Id:\t${it.receiptId}" } ?: ""
    }

    @Bindable
    fun getDate(): String {
        return receiptSummaryLiveData.value?.let { "Date:\t${it.date}" } ?: ""
    }

    @Bindable
    fun getCategoryName(): String {
        return receiptSummaryLiveData.value?.let { "Category:\t\t\t${it.category}" } ?: ""
    }

    @Bindable
    fun getAmount(): String {
        return receiptSummaryLiveData.value?.let { "Rs.${it.totalAmountWithTax}" } ?: ""
    }

    @Bindable
    fun getDiscount(): String {
        return receiptSummaryLiveData.value?.let { "${it.discountInPercent}%" } ?: ""
    }

    @Bindable
    fun getTax(): String {
        return receiptSummaryLiveData.value?.let { "${it.taxesInPercent}%" } ?: ""
    }
}