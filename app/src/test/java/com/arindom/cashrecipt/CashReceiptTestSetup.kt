package com.arindom.cashrecipt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class CashReceiptTestSetup {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }
}