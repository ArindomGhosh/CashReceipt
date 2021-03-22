package com.arindom.cashrecipt.views.widgets.receiptdetails

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.ResultStates
import com.arindom.cashrecipt.network.responses.ReceiptDetailsLayout
import com.arindom.cashrecipt.views.CashReceiptViewModel
import com.arindom.cashrecipt.views.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ReceiptDetailsViewModel @Inject constructor(
    private val mCashReceiptService: CashReceiptService,
) : CashReceiptViewModel() {
    private val layoutLiveData = MediatorLiveData<UIState<ReceiptDetailsLayout>>()
    fun fetchReceiptListLayout() {
        val resultStates = mCashReceiptService
            .getReceiptDetailsLayout()
            .map { result ->
                when (result) {
                    is ResultStates.Failure -> UIState(loading = false, error = result.error)
                    is ResultStates.Success -> UIState(loading = false, data = result.data)
                }
            }
            .onStart { emit(UIState(true)) }
            .asLiveData(Dispatchers.Default + viewModelScope.coroutineContext)
        layoutLiveData.addSource(resultStates) {
            layoutLiveData.postValue(it)
        }
    }

    fun getLayoutLiveDate(): LiveData<UIState<ReceiptDetailsLayout>> {
        return layoutLiveData
    }

    @Bindable
    fun isProgressbarVisible(): Boolean {
        return layoutLiveData.value?.loading ?: true
    }
}