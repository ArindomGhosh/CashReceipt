package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.ResultStates
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.UIState
import com.arindom.cashrecipt.views.widgets.receiptlist.ReceiptListViewModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception
import kotlin.random.Random

class ReceiptListViewModelTesting : CashReceiptTestSetup() {

    @MockK
    private lateinit var mockCashReceiptService: CashReceiptService

    @Test
    fun `should return receipt list on success`() {
        val mockCashReceiptObserver = mockk<Observer<UIState<List<CashReceipt>>>>(relaxed = true)
        val receiptListViewModel = ReceiptListViewModel(mockCashReceiptService)
        val cashReceiptCaptor = mutableListOf<UIState<List<CashReceipt>>>()
        receiptListViewModel.getCashReceiptLiveDate().observeForever(mockCashReceiptObserver)
        every { mockCashReceiptService.getCashReceiptList() } returns flow {
            emit(getReceiptListSuccessResponse())
        }
        receiptListViewModel.fetchReceiptList()
        verify(exactly = 2) {
            mockCashReceiptObserver.onChanged(capture(cashReceiptCaptor))
        }
        assertTrue(cashReceiptCaptor[0].loading)
        assertNotNull(cashReceiptCaptor[1].data)
        assertEquals("Arindom Ghosh",cashReceiptCaptor[1].data!![0].customerName)
    }

    @Test
    fun `should return failure on Exception`(){
        val mockCashReceiptObserver = mockk<Observer<UIState<List<CashReceipt>>>>(relaxed = true)
        val receiptListViewModel = ReceiptListViewModel(mockCashReceiptService)
        val cashReceiptCaptor = mutableListOf<UIState<List<CashReceipt>>>()
        receiptListViewModel.getCashReceiptLiveDate().observeForever(mockCashReceiptObserver)
        every { mockCashReceiptService.getCashReceiptList() } returns flow {
            emit(getReceiptListFailedResponse())
        }
        receiptListViewModel.fetchReceiptList()
        verify(exactly = 2) {
            mockCashReceiptObserver.onChanged(capture(cashReceiptCaptor))
        }
        assertTrue(cashReceiptCaptor[0].loading)
        assertNotNull(cashReceiptCaptor[1].error)
        assertEquals("No List found!!",cashReceiptCaptor[1].error!!.message)
    }


    private fun getReceiptListSuccessResponse(): ResultStates.Success<List<CashReceipt>> {
        return ResultStates.Success(listOf(CashReceipt(
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
        )))
    }
    private fun getReceiptListFailedResponse(): ResultStates.Failure {
        return ResultStates.Failure(Exception("No List found!!"))
    }
}