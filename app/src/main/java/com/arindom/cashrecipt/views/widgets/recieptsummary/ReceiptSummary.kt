package com.arindom.cashrecipt.views.widgets.recieptsummary

import com.arindom.cashrecipt.network.responses.CashReceipt

data class ReceiptSummary(
    val receiptId: Int,
    val date: String,
    val category: String,
    val totalAmountWithTax: Double,
    val taxesInPercent: Double,
    val discountInPercent: Double = 0.0
){
    constructor(cashReceipt: CashReceipt):this(
        receiptId = cashReceipt.receiptId,
        date = cashReceipt.date,
        category = cashReceipt.category,
        totalAmountWithTax = cashReceipt.totalAmountWithTax,
        discountInPercent = cashReceipt.discountInPercent,
        taxesInPercent = cashReceipt.taxesInPercent
    )
}
