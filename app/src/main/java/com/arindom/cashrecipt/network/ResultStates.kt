package com.arindom.cashrecipt.network

import java.lang.Exception

sealed class ResultStates<T>{
    data class Success<T>(val data:T):ResultStates<T>()
    data class Failure(val error:Exception):ResultStates<Nothing>()
}
