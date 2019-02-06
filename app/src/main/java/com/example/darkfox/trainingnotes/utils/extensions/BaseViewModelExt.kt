package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.arch.base.ui.BaseViewModel

fun BaseViewModel.withProgress(code:()->Unit){
    startProgress()
    code.invoke()
    stopProgress()
}