package com.example.darkfox.trainingnotes.models.dto

sealed class Result<T> {

    class Success<T:Any>(val data:T) : Result<T>()
    class Fail<T:Any>(val cause:Throwable) : Result<T>()
}