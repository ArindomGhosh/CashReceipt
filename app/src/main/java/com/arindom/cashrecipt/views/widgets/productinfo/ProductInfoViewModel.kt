package com.arindom.cashrecipt.views.widgets.productinfo

import androidx.lifecycle.*
import com.arindom.cashrecipt.exception.NoViewModelFoundException
import com.arindom.cashrecipt.network.responses.CashReceipt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor() :
    ViewModel() {
    val productDetailListLiveData = MutableLiveData<List<CashReceipt.ItemWithPrice>>()
    fun setItemPriceList(mItemWithPriceList: List<CashReceipt.ItemWithPrice>) {
        productDetailListLiveData.postValue(mItemWithPriceList)
    }
}