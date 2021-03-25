package com.arindom.cashrecipt.models

import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.widgets.recieptsummary.ReceiptSummary
import org.junit.Assert.assertEquals

import org.junit.Test
import kotlin.random.Random

class ReceiptSummaryTest {

    @Test
    fun `should convert CashReceipt to ReceiptSummary`(){
        val mockCashReceipt = CashReceipt(
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
        val receiptSummary = ReceiptSummary(cashReceipt = mockCashReceipt)
        assertEquals(mockCashReceipt.taxesInPercent, receiptSummary.taxesInPercent,0.0)
        assertEquals(mockCashReceipt.category, receiptSummary.category)
        assertEquals(mockCashReceipt.date, receiptSummary.date)
        assertEquals(mockCashReceipt.discountInPercent, receiptSummary.discountInPercent,0.0)
        assertEquals(mockCashReceipt.receiptId, receiptSummary.receiptId)
        assertEquals(mockCashReceipt.totalAmountWithTax, receiptSummary.totalAmountWithTax,0.0)
    }
}