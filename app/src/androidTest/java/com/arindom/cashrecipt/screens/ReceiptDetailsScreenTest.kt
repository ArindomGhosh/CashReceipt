package com.arindom.cashrecipt.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.arindom.cashrecipt.R
import com.arindom.cashrecipt.util.launchFragmentInHiltContainer
import com.arindom.cashrecipt.views.screens.details.ReceiptDetailsScreen
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class ReceiptDetailsScreenTest {

    @Test
    fun fragmentContainerIsDisplayed() {
        launchFragmentInHiltContainer<ReceiptDetailsScreen> { ReceiptDetailsScreen() }
        onView(withId(R.id.fr_container_receipt_details))
            .check(matches(isDisplayed()))
    }
}