package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.exception.UserNotFoundException
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.ResultStates
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.UIState
import com.arindom.cashrecipt.views.screens.details.ReceiptDetailsScreenEvent
import com.arindom.cashrecipt.views.screens.details.ReceiptDetailsScreenViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class ReceiptDetailsScreenViewModelTest:CashReceiptTestSetup() {

    @MockK
    private lateinit var mCashReceiptService: CashReceiptService

    @Test
    fun `onEventTriggered should fetch receipt details on success of FetchNewReceiptForReceiptId Event`(){
        val receiptDetailsScreenViewModel = ReceiptDetailsScreenViewModel(mCashReceiptService)
        val cashReceiptCaptor = mutableListOf<UIState<CashReceipt>>()
        val mockCashReceiptObserver: Observer<UIState<CashReceipt>> = mockk(relaxed = true)
        receiptDetailsScreenViewModel.getCashReceiptLiveDate().observeForever(mockCashReceiptObserver)
        val mockResponseFlow = flow<ResultStates<CashReceipt>> {
            emit(ResultStates.Success((CashReceipt(
                receiptId = 85,
                date = "2021:02:15",//yyyy:mm:dd
                category = "Grocery",
                customerName = "Arindom Ghosh",
                itemListWithPrice = listOf(
                    CashReceipt.ItemWithPrice(
                        productId = Random.nextInt(200, 400),
                        productName = "Eggs",
                        productPrice = 152.50
                    )
                ),
                totalAmountWithTax = 180.00,
                taxesInPercent = 18.00
            ))))
        }
        every { mCashReceiptService.getCashReceiptDetails(any()) } returns mockResponseFlow
        receiptDetailsScreenViewModel.onReceiptDetailsEventTrigger(ReceiptDetailsScreenEvent.FetchNewReceiptForReceiptIdScreen(45))

        verify(exactly = 2) { mockCashReceiptObserver.onChanged(capture(cashReceiptCaptor))  }
        assertTrue(cashReceiptCaptor[0].loading)
        assertNotNull(cashReceiptCaptor[1].data)
    }

    @Test
    fun `onEventTriggered should return failure on error of FetchNewReceiptForReceiptId Event`(){
        val receiptDetailsScreenViewModel = ReceiptDetailsScreenViewModel(mCashReceiptService)
        val cashReceiptCaptor = mutableListOf<UIState<CashReceipt>>()
        val mockCashReceiptObserver: Observer<UIState<CashReceipt>> = mockk(relaxed = true)
        receiptDetailsScreenViewModel.getCashReceiptLiveDate().observeForever(mockCashReceiptObserver)
        val mockResponseFlow = flow {
            emit(ResultStates.Failure(UserNotFoundException("45")))
        }
        every { mCashReceiptService.getCashReceiptDetails(any()) } returns mockResponseFlow
        receiptDetailsScreenViewModel.onReceiptDetailsEventTrigger(ReceiptDetailsScreenEvent.FetchNewReceiptForReceiptIdScreen(45))

        verify(exactly = 2) { mockCashReceiptObserver.onChanged(capture(cashReceiptCaptor))  }
        assertTrue(cashReceiptCaptor[0].loading)
        assertThat(cashReceiptCaptor[1].error, CoreMatchers.instanceOf(UserNotFoundException::class.java))
    }

}