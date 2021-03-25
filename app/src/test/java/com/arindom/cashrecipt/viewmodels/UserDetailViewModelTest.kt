package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetails
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetailsViewModel
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThat
import org.junit.Test

class UserDetailViewModelTest : CashReceiptTestSetup() {

    @Test
    fun `should return user details when LiveData value is set`() {
        val userDetailsViewModel = UserDetailsViewModel()
        val mockUserDetailsObserver = mockk<Observer<UserDetails>>(relaxed = true)
        userDetailsViewModel.userDetailsLiveDate.observeForever(mockUserDetailsObserver)
        val userDetailsSlot = slot<UserDetails>()
        userDetailsViewModel.setUserDetails(getMockUserDetails())
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