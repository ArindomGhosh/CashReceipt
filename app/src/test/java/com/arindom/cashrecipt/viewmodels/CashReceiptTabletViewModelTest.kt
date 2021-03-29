package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.screens.detailstab.CashReceiptTabletEvent
import com.arindom.cashrecipt.views.screens.detailstab.CashReceiptTabletViewModel
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import kotlin.random.Random


class CashReceiptTabletViewModelTest:CashReceiptTestSetup(){

    @Test
    fun `should return CashReceipt when CashReceipt LiveData is set`(){
        val cashReceiptTabletViewModel = CashReceiptTabletViewModel()
        val mockCashReceiptObserver = mockk<Observer<CashReceipt>>(relaxed = true)
        val cashReceiptCaptor = slot<CashReceipt>()
        cashReceiptTabletViewModel.getCashReceiptLiveData().observeForever(mockCashReceiptObserver)
        cashReceiptTabletViewModel.onCashReceiptEventTrigger(CashReceiptTabletEvent.ShowCashReceiptDetailsEvents(getCashReceiptStub()))
        verify { mockCashReceiptObserver.onChanged(capture(cashReceiptCaptor)) }
    }

    private fun getCashReceiptStub():CashReceipt{
        return CashReceipt(
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
        )
    }
}