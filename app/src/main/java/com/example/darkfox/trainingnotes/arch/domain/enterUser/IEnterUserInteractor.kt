package com.example.darkfox.trainingnotes.arch.domain.enterUser

import com.example.darkfox.trainingnotes.dto.Account

interface IEnterUserInteractor {
    fun checkEmail(email:String,success:()->Unit,error:(String)->Unit)
    fun checkPassword(password:String,success:()->Unit,error:(String)->Unit)
    suspend fun signIn(email:String?, password: String?, success:(Account)->Unit, error:(java.lang.Exception)->Unit)
    suspend fun register(email:String?, password: String?, success:(Account)->Unit, error:(java.lang.Exception)->Unit)
}