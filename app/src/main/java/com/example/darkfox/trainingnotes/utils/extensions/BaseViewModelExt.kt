package com.example.darkfox.trainingnotes.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.darkfox.trainingnotes.arch.base.ui.BaseViewModel

fun BaseViewModel.withProgress(code:()->Unit){
    startProgress()
    code.invoke()
    stopProgress()
}
