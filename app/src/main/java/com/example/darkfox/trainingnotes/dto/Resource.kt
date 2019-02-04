package com.example.darkfox.trainingnotes.dto

import com.example.darkfox.trainingnotes.utils.helpers.states.RequestState


data class Resource<T>(
        val state: RequestState,
        val data: T? = null,
        val errorMessage:String = ""
        )