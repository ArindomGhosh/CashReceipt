package com.arindom.cashrecipt.views.widgets.receiptlist

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.arindom.cashrecipt.exception.NoViewModelFoundException
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.ResultStates
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.CashReceiptViewModel
import com.arindom.cashrecipt.views.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ReceiptListViewModel @Inject constructor(
    private val mCashReceiptService: CashReceiptService,
) : CashReceiptViewModel() {
    var showCategory: Boolean = true
    private val cashReceiptLiveData = MediatorLiveData<UIState<List<CashReceipt>>>()
    fun fetchReceiptList() {
        val resultStates = mCashReceiptService
            .getCashReceiptList()
            .map { result ->
                when (result) {
                    is ResultStates.Failure -> UIState(
                        false,
                        error = result.error
                    )
                    is ResultStates.Success -> UIState(
                        loading = false,
                        data = result.data
                    )
                }
            }
            .onStart { emit(UIState(loading = true)) }
            .asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)
        cashReceiptLiveData.addSource(resultStates) {
            cashReceiptLiveData.postValue(it)
        }
    }

    fun getCashReceiptLiveDate(): LiveData<UIState<List<CashReceipt>>> {
        return cashReceiptLiveData
    }

    @Bindable
    fun isProgressbarVisible(): Boolean {
        return cashReceiptLiveData.value?.loading ?: true
    }
}