package com.arindom.cashrecipt

import android.app.Application
import com.arindom.cashrecipt.network.CashReceiptService
import com.arindom.cashrecipt.network.FakeCashReceiptServiceImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CashReceiptApplication :Application()