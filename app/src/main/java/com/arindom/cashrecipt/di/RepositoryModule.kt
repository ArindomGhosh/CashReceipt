package com.arindom.cashrecipt.di

import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.FakeCashReceiptServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsCashReceiptService(cashReceiptService: FakeCashReceiptServiceImpl):CashReceiptService
}