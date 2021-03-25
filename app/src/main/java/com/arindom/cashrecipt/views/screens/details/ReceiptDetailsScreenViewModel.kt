package com.arindom.cashrecipt.views.screens.details

import androidx.databinding.Bindable
import androidx.lifecycle.*
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
class ReceiptDetailsScreenViewModel @Inject constructor(
    private val mCashReceiptService: CashReceiptService,
) : CashReceiptViewModel() {
    private val mCasReceiptUiState = MediatorLiveData<UIState<CashReceipt>>()
    fun fetchReceiptDetails(receiptId: Int) {
        val resultUIState = mCashReceiptService
            .getCashReceiptDetails(receiptId)
            .map { result ->
                when (result) {
                    is ResultStates.Failure -> UIState(loading = false, error = result.error)
                    is ResultStates.Success -> UIState(loading = false, data = result.data)
                }
            }
            .onStart { emit(UIState(loading = true)) }
            .asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)

        mCasReceiptUiState.addSource(
            resultUIState
        ) {
            mCasReceiptUiState.postValue(it)
        }
    }

    fun getCashReceiptLiveDate(): LiveData<UIState<CashReceipt>> {
        return mCasReceiptUiState
    }

    @Bindable
    fun isProgressbarVisible(): Boolean {
        return mCasReceiptUiState.value?.loading ?: true
    }
}