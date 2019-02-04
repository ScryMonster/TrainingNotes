package com.example.darkfox.trainingnotes.utils.helpers.states

sealed class RequestState {
    object Loading : RequestState()
    object Success : RequestState()
    object Error : RequestState()
}