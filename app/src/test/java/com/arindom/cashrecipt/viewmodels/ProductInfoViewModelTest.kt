package com.arindom.cashrecipt.viewmodels

import androidx.lifecycle.Observer
import com.arindom.cashrecipt.CashReceiptTestSetup
import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.views.widgets.productinfo.ProductInfoViewModel
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class ProductInfoViewModelTest : CashReceiptTestSetup() {
    @Test
    fun `should return list of product with price when live data value is set`() {
        val productViewModel = ProductInfoViewModel()
        val mockProductListObserver =
            mockk<Observer<List<CashReceipt.ItemWithPrice>>>(relaxed = true)
        val productListCaptor = slot<List<CashReceipt.ItemWithPrice>>()
        productViewModel.productDetailListLiveData.observeForever(mockProductListObserver)
        productViewModel.setItemPriceList(getProductPriceList())
        verify { mockProductListObserver.onChanged(capture(productListCaptor)) }
        assertEquals(2, productListCaptor.captured.size)
    }

    private fun getProductPriceList(): List<CashReceipt.ItemWithPrice> {
        return listOf(
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Oranges",
                productPrice = 100.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Banana",
                productPrice = 60.00
            ),
        )
    }
}