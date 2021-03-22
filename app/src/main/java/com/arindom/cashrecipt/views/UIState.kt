package com.arindom.cashrecipt.views

import java.lang.Exception

data class UIState<T>(
    val loading: Boolean,
    val error: Exception? = null,
    val data: T? = null
)