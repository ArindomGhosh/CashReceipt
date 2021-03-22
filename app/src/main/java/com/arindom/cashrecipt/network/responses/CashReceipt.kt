package com.arindom.cashrecipt.network.responses

data class CashReceipt(
    val receiptId:Int,
    val date:String,
    val category:String,
    val customerName:String,
    val itemListWithPrice: List<ItemWithPrice>,
    val totalAmountWithTax:Double,
    val taxesInPercent:Double,
    val discountInPercent:Double =0.0
){
    data class  ItemWithPrice(
        val productImage:String= "",
        val productId: Int,
        val productName:String,
        val productPrice: Double
    )
}
