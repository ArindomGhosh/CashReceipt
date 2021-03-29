package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.views.widgets.recieptsummary.ReceiptSummary
import com.arindom.cashrecipt.views.widgets.recieptsummary.ReceiptSummaryViewModel
import com.arindom.cashrecipt.views.widgets.recieptsummary.ReceiptSummaryWidgetEvent
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test

class ReceiptSummaryViewModelTest : CashReceiptTestSetup() {
    @Test
    fun `should return receipt summary for FetchReceiptSummaryEvent`() {
        val receiptSummaryViewModel = ReceiptSummaryViewModel()
        val mockReceiptSummaryObserver = mockk<Observer<ReceiptSummary>>(relaxed = true)
        val receiptSummarySlot = slot<ReceiptSummary>()
        receiptSummaryViewModel.getReceiptSummaryLiveData().observeForever(mockReceiptSummaryObserver)
        receiptSummaryViewModel.onReceiptSummaryEventTrigger(ReceiptSummaryWidgetEvent.FetchReceiptSummaryEvent(getReceiptSummary()))
        verify { mockReceiptSummaryObserver.onChanged(capture(receiptSummarySlot)) }
        assertThat(receiptSummarySlot.captured, CoreMatchers.instanceOf(ReceiptSummary::class.java))
    }

    private fun getReceiptSummary(): ReceiptSummary {
        return ReceiptSummary(receiptId = 5,
            taxesInPercent = 18.00,
            discountInPercent = 0.00,
            totalAmountWithTax = 500.00,
            category = "Grocery",
            date = "2021:02:15")
    }
}