package com.arindom.cashrecipt.network

import com.arindom.cashrecipt.network.responses.CashReceipt
import com.arindom.cashrecipt.network.responses.ReceiptDetailsLayout
import kotlinx.coroutines.flow.Flow


interface CashReceiptService {
    fun getCashReceiptList(): Flow<ResultStates<out List<CashReceipt>>>
    fun getCashReceiptDetails(id: Int): Flow<ResultStates<out CashReceipt>>
    fun getReceiptDetailsLayout(): Flow<ResultStates<out ReceiptDetailsLayout>>
}