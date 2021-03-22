package com.arindom.cashrecipt.di

import android.content.Context
import com.arindom.cashrecipt.CashReceiptApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context): CashReceiptApplication {
        return app as CashReceiptApplication
    }
}