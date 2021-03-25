package com.arindom.cashrecipt.models

import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.widgets.userdetails.UserDetails
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class UserDetailsTest {

    @Test
    fun `should covert CashReceipt to UserDetails`() {
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
        val userDetails = UserDetails(cashReceipt = mockCashReceipt)
        assertEquals(mockCashReceipt.customerName, userDetails.name)
    }
}