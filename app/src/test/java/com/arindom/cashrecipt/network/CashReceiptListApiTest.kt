package com.arindom.cashrecipt.network

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test

class CashReceiptListApiTest {

    @Test
    fun `should get receipt list`() {
        val cashReceiptService = FakeCashReceiptServiceImpl()
        val resultStates = cashReceiptService.getCashReceiptList()
        runBlocking {
            resultStates.collect {
                assertThat(it, CoreMatchers.instanceOf(ResultStates.Success::class.java))
            }
        }
    }
}