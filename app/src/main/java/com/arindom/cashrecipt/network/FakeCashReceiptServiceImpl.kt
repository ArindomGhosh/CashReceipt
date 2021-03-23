package com.arindom.cashrecipt.network

import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.network.responses.ReceiptDetailsLayout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

/**
 * Mock response to showcase the UI changes based on API calls
 * This can further be explored for setting other params of UI as
 * well.
 *
 * Change between receiptDetailsLayout and  receiptDetailsLayoutWithoutProductInfo
 * to see the UI changes in app based on mock API response.
 * You can also change the order of the UI displayed by changing the order of the
 * screen in the response.
 * */
val receiptDetailsLayout = ReceiptDetailsLayout(
    page = "ReceiptDetailsScreen",
    rows = listOf("UserInfo", "ProductInfo", "ReceiptInfo")
)

val receiptDetailsLayoutWithoutProductInfo = ReceiptDetailsLayout(
    page = "ReceiptDetailsScreen",
    rows = listOf("UserInfo", "ReceiptInfo")
)

val receiptList = listOf(
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
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
    ),
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
        date = "2021:02:15",//yyyy:mm:dd
        category = "Fruits",
        customerName = "Arindom Ghosh",
        itemListWithPrice = listOf(
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
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Grapes",
                productPrice = 40.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Dates",
                productPrice = 200.00
            ),
        ),
        totalAmountWithTax = 472.00,
        taxesInPercent = 18.00
    ),
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
        date = "2021:02:15",//yyyy:mm:dd
        category = "Vegitables",
        customerName = "Arindom Ghosh",
        itemListWithPrice = listOf(
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Potatoes",
                productPrice = 50.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Onion",
                productPrice = 80.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Tomatoes",
                productPrice = 60.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Spinach",
                productPrice = 50.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Ladies finger",
                productPrice = 90.00
            ),
        ),
        totalAmountWithTax = 389.00,
        taxesInPercent = 18.00
    ),
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
        date = "2021:02:15",//yyyy:mm:dd
        category = "Grocery",
        customerName = "Arindom Ghosh",
        itemListWithPrice = listOf(
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Bread",
                productPrice = 40.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Butter",
                productPrice = 35.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Jam",
                productPrice = 50.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Mortein",
                productPrice = 180.00
            ),
        ),
        totalAmountWithTax = 359.00,
        taxesInPercent = 18.00
    ),
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
        date = "2021:02:15",//yyyy:mm:dd
        category = "Beverages",
        customerName = "Arindom Ghosh",
        itemListWithPrice = listOf(
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Coffee",
                productPrice = 100.50
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Tea",
                productPrice = 40.50
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Juice",
                productPrice = 150.00
            ),
        ),
        totalAmountWithTax = 343.38,
        taxesInPercent = 18.00
    ),
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
        date = "2021:02:15",//yyyy:mm:dd
        category = "Utensils",
        customerName = "Arindom Ghosh",
        itemListWithPrice = listOf(
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Plates",
                productPrice = 50.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Rice Bowl",
                productPrice = 30.00
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Coffee mug",
                productPrice = 50.00
            ),
        ),
        totalAmountWithTax = 153.40,
        taxesInPercent = 18.00
    ),
    CashReceipt(
        receiptId = Random.nextInt(0, 100),
        date = "2021:02:15",//yyyy:mm:dd
        category = "Furniture",
        customerName = "Arindom Ghosh",
        itemListWithPrice = listOf(
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Table",
                productPrice = 1500.50
            ),
            CashReceipt.ItemWithPrice(
                productId = Random.nextInt(200, 400),
                productName = "Chair",
                productPrice = 800.50
            ),
        ),
        totalAmountWithTax = 2715.18,
        taxesInPercent = 18.00
    ),
)


class FakeCashReceiptServiceImpl @Inject constructor() : CashReceiptService {
    override fun getCashReceiptList(): Flow<ResultStates<out List<CashReceipt>>> {
        return flow {
            kotlinx.coroutines.delay(1000)
            emit(ResultStates.Success(receiptList))
        }
    }

    override fun getCashReceiptDetails(id: Int): Flow<ResultStates<out CashReceipt>> {
        return flow {
            kotlinx.coroutines.delay(1000)
            emit(ResultStates.Success(receiptList.first { it.receiptId == id }))
        }
    }

    override fun getReceiptDetailsLayout(): Flow<ResultStates<out ReceiptDetailsLayout>> {
        return flow {
            kotlinx.coroutines.delay(1000)
            emit(ResultStates.Success(receiptDetailsLayout))// replace with receiptDetailsLayoutWithoutProductInfo to change  Receipt Details UI
        }
    }
}