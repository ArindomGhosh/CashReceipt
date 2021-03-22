package com.arindom.cashrecipt.views.widgets.userdetails

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.arindom.cashrecipt.exception.NoViewModelFoundException
import com.arindom.cashrecipt.views.CashReceiptViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor():CashReceiptViewModel() {
    val userDetailsLiveDate = MutableLiveData<UserDetails>()
    fun setUserDetails(userDetails: UserDetails){
        userDetailsLiveDate.postValue(userDetails)
    }

    @Bindable
    fun getUserName():String{
        return userDetailsLiveDate.value?.name?:""
    }
}