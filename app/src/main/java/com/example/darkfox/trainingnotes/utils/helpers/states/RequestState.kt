package com.example.darkfox.trainingnotes.utils.helpers.states

sealed class RequestState {
    object Loading_START : RequestState()
    object Loading_STOP : RequestState()
    object Success : RequestState()
    class Error(val e:Exception) : RequestState()
}