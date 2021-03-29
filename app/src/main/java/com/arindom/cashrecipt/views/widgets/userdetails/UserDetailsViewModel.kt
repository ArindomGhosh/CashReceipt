package com.arindom.cashrecipt.views.widgets.userdetails

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.arindom.cashrecipt.views.CashReceiptViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class UserDetailsWidgetEvent {
    data class FetchUserDetailsEvent(val userDetails: UserDetails) : UserDetailsWidgetEvent()
}

@HiltViewModel
class UserDetailsViewModel @Inject constructor() : CashReceiptViewModel() {
    private val userDetailsLiveData = MutableLiveData<UserDetails>()

    fun getUserDetailsLiveData(): LiveData<UserDetails> {
        return userDetailsLiveData
    }

    fun onUserDetailsEventTrigger(userDetailsEvent: UserDetailsWidgetEvent) {
        when (userDetailsEvent) {
            is UserDetailsWidgetEvent.FetchUserDetailsEvent -> userDetailsLiveData.postValue(
                userDetailsEvent.userDetails)
        }
    }

    @Bindable
    fun getUserName(): String {
        return userDetailsLiveData.value?.name ?: ""
    }
}