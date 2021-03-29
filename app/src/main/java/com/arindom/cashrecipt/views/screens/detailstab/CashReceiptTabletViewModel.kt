package com.arindom.cashrecipt.views.screens.detailstab

import androidx.lifecycle.*
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.ResultStates
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.network.responses.ReceiptDetailsLayout
import com.arindom.cashrecipt.views.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

sealed class CashReceiptTabletEvent {
    data class ShowCashReceiptDetailsEvents(val cashReceipt: CashReceipt) : CashReceiptTabletEvent()
}

@HiltViewModel
class CashReceiptTabletViewModel @Inject constructor(
) : ViewModel() {
    private val cashReceiptLiveData = MutableLiveData<CashReceipt>()

    fun getCashReceiptLiveData(): LiveData<CashReceipt> {
        return cashReceiptLiveData
    }

    fun onCashReceiptEventTrigger(cashReceiptDetailsEvents: CashReceiptTabletEvent) {
        when(cashReceiptDetailsEvents){
            is CashReceiptTabletEvent.ShowCashReceiptDetailsEvents -> cashReceiptLiveData.postValue(cashReceiptDetailsEvents.cashReceipt)
        }
    }

}