package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.models.dto.Header
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


infix fun Request.addHeader(header: Header):Request {
    header.apply { newBuilder().addHeader(name, headerValue) }
    return this
}


fun header(code:Header.()->Unit)  = Header().apply(code)

infix fun Request.build(chain: Interceptor.Chain):Response = chain.proceed(this)

inline infix fun <reified T> Gson.fromJson(obj:String) = fromJson(obj,T::class.java)

