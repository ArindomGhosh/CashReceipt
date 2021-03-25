package com.arindom.cashrecipt.network

import com.arindom.cashrecipt.exception.UserNotFoundException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class CashReceiptApiTest {

    @Test
    fun `should return UserNotFoundException provided random id`() {
        val cashReceiptService = FakeCashReceiptServiceImpl()
        val resultStates =cashReceiptService.getCashReceiptDetails(5)
        runBlocking {
            resultStates.collect{
                assertThat(it, CoreMatchers.instanceOf(ResultStates.Failure::class.java))
                val failureState = it as ResultStates.Failure
                assertThat(failureState.error, CoreMatchers.instanceOf(UserNotFoundException::class.java))
            }
        }
    }

    @Test
    fun `should return receipt detail provided correct id`(){
        val cashReceiptService = FakeCashReceiptServiceImpl()
        val resultStates =cashReceiptService.getCashReceiptDetails(85)
        runBlocking {
            resultStates.collect{
                assertThat(it, CoreMatchers.instanceOf(ResultStates.Success::class.java))
            }
        }
    }
}