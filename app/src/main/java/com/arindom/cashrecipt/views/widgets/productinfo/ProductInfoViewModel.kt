package com.arindom.cashrecipt.views.widgets.productinfo

import androidx.lifecycle.*
import com.arindom.cashrecipt.network.responses.CashReceipt
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class ProductInfoWidgetEvent {
    data class FetchProductListEvent(val productList: List<CashReceipt.ItemWithPrice>):ProductInfoWidgetEvent()
}

@HiltViewModel
class ProductInfoViewModel @Inject constructor() :
    ViewModel() {
    private val productDetailListLiveData = MutableLiveData<List<CashReceipt.ItemWithPrice>>()

    fun getProductDetailsLiveData(): LiveData<List<CashReceipt.ItemWithPrice>> {
        return productDetailListLiveData
    }

    fun onProductInfoEventTrigger(productListEvent: ProductInfoWidgetEvent) {
        when(productListEvent){
            is ProductInfoWidgetEvent.FetchProductListEvent ->  productDetailListLiveData.postValue(productListEvent.productList)
        }

    }
}