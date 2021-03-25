package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.exception.LayoutNotReceivedException
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.ResultStates
import com.arindom.cashrecipt.network.responses.ReceiptDetailsLayout
import com.arindom.cashrecipt.views.UIState
import com.arindom.cashrecipt.views.widgets.receiptdetails.ReceiptDetailsViewModel
import io.mockk.CaptureMatcher
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

class ReceiptDetailsViewModelTest : CashReceiptTestSetup() {

    @MockK
    private lateinit var mockCashReceiptService: CashReceiptService

    @Test
    fun `should return layout on success`() {
        val receiptDetailsViewModel = ReceiptDetailsViewModel(mockCashReceiptService)
        val mockReceiptDetailsLayoutObserver =
            mockk<Observer<UIState<ReceiptDetailsLayout>>>(relaxed = true)
        receiptDetailsViewModel.getLayoutLiveDate().observeForever(mockReceiptDetailsLayoutObserver)
        val receiptDetailsLayoutCaptor = mutableListOf<UIState<ReceiptDetailsLayout>>()
        every { mockCashReceiptService.getReceiptDetailsLayout() } returns flow {
            emit(getReceiptLayoutResponseOnSuccess())
        }
        receiptDetailsViewModel.fetchReceiptListLayout()
        verify(exactly = 2) {
            mockReceiptDetailsLayoutObserver.onChanged(capture(receiptDetailsLayoutCaptor))
        }
        assertTrue(receiptDetailsLayoutCaptor[0].loading)
        assertThat(receiptDetailsLayoutCaptor[1].data,
            CoreMatchers.instanceOf(ReceiptDetailsLayout::class.java))
    }


    @Test
    fun `should return failure on exception`() {
        val receiptDetailsViewModel = ReceiptDetailsViewModel(mockCashReceiptService)
        val mockReceiptDetailsLayoutObserver =
            mockk<Observer<UIState<ReceiptDetailsLayout>>>(relaxed = true)
        receiptDetailsViewModel.getLayoutLiveDate().observeForever(mockReceiptDetailsLayoutObserver)
        val receiptDetailsLayoutCaptor = mutableListOf<UIState<ReceiptDetailsLayout>>()
        every { mockCashReceiptService.getReceiptDetailsLayout() } returns flow {
            emit(ResultStates.Failure(LayoutNotReceivedException()))
        }
        receiptDetailsViewModel.fetchReceiptListLayout()
        verify(exactly = 2) {
            mockReceiptDetailsLayoutObserver.onChanged(capture(receiptDetailsLayoutCaptor))
        }
        assertTrue(receiptDetailsLayoutCaptor[0].loading)
        assertThat(receiptDetailsLayoutCaptor[1].error,
            CoreMatchers.instanceOf(LayoutNotReceivedException::class.java))
    }

    private fun getReceiptLayoutResponseOnSuccess(): ResultStates.Success<ReceiptDetailsLayout> {
        return ResultStates.Success<ReceiptDetailsLayout>(ReceiptDetailsLayout(
            page = "ReceiptDetailsScreen",
            rows = listOf("UserInfo", "ReceiptInfo")
        ))
    }
}