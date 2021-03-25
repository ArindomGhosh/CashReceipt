package com.arindom.cashrecipt.exception

import java.lang.Exception

class NoViewModelFoundException :Exception("No ViewModel found for the class!")
class  UserNotFoundException(userId:String) :Exception("No user found for the userId: $userId ")
class LayoutNotReceivedException:Exception("No layout received!!")