package com.example.darkfox.trainingnotes.utils.enums

enum class EnterUserFlow {
    LOGIN,
    REGISTER
}

fun String.convertToEnterUserFlow(): EnterUserFlow = when {
    this == EnterUserFlow.LOGIN.name -> EnterUserFlow.LOGIN
    this == EnterUserFlow.REGISTER.name -> EnterUserFlow.REGISTER
    else -> EnterUserFlow.LOGIN

}