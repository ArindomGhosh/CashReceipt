package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetails
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetailsViewModel
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetailsWidgetEvent
import io.mockk.mockk
import io.mockk.slot
import org.junit.Test
import io.mockk.verify
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse

class UserDetailViewModelTest : CashReceiptTestSetup() {

    @Test
    fun `should return user details when LiveData value is set`() {
        val userDetailsViewModel = UserDetailsViewModel()
        val mockUserDetailsObserver = mockk<Observer<UserDetails>>(relaxed = true)
        val userDetailsSlot = slot<UserDetails>()
        userDetailsViewModel.getUserDetailsLiveData().observeForever(mockUserDetailsObserver)
        userDetailsViewModel.onUserDetailsEventTrigger(UserDetailsWidgetEvent.FetchUserDetailsEvent(getMockUserDetails()))
        verify { mockUserDetailsObserver.onChanged(capture(userDetailsSlot)) }
        assertFalse(userDetailsSlot.isNull)
        assertThat(userDetailsSlot.captured,CoreMatchers.instanceOf(UserDetails::class.java))
    }

    private fun getMockUserDetails():UserDetails{
        return UserDetails(
            name = "Alex",
            userImageUrl = "http:\\a.alex.png"
        )
    }
}