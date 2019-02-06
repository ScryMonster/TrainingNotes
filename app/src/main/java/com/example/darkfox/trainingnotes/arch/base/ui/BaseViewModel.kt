package com.example.darkfox.trainingnotes.arch.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.darkfox.trainingnotes.utils.helpers.states.RequestState

open class BaseViewModel :ViewModel(){

    val requestState = MutableLiveData<RequestState>()

    fun startProgress(){
        requestState.value = RequestState.Loading_START
    }

    fun stopProgress(){
        requestState.value = RequestState.Loading_STOP
    }
}