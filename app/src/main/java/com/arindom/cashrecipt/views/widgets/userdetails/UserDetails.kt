package com.arindom.cashrecipt.views.widgets.userdetails

import com.arindom.cashrecipt.network.responses.CashReceipt

data class UserDetails(val name:String,
                       val userImageUrl:String){
    constructor(cashReceipt: CashReceipt):this(
        name = cashReceipt.customerName,
        userImageUrl= ""
    )
}
