package com.arindom.cashrecipt.network

import com.arindom.cashrecipt.exception.UserNotFoundException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

class CashReceiptApiTest {

    @Test
    fun `should return UserNotFoundException provided random id`() {
        val cashReceiptService = FakeCashReceiptServiceImpl()
        val resultStates = cashReceiptService.getCashReceiptDetails(5)
        runBlockingTest {
            resultStates.collect {
                assertThat(it, CoreMatchers.instanceOf(ResultStates.Failure::class.java))
                val failureState = it as ResultStates.Failure
                assertThat(failureState.error,
                    CoreMatchers.instanceOf(UserNotFoundException::class.java))
            }
        }
    }

    @Test
    fun `should return receipt detail provided correct id`() {
        val cashReceiptService = FakeCashReceiptServiceImpl()
        val resultStates = cashReceiptService.getCashReceiptDetails(85)
        runBlockingTest {
            resultStates.collect {
                assertThat(it, CoreMatchers.instanceOf(ResultStates.Success::class.java))
            }
        }
    }

    @Test
    fun `should return receipt details screen layout `() {
        val cashReceiptService = FakeCashReceiptServiceImpl()
        val resultStates =cashReceiptService.getReceiptDetailsLayout()
        runBlockingTest {
            resultStates.collect {
                assertThat(it, CoreMatchers.instanceOf(ResultStates.Success::class.java))
                val successState = it as ResultStates.Success
                assertTrue(successState.data.rows.isNotEmpty())
            }
        }
    }
}