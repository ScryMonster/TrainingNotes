package com.example.darkfox.trainingnotes.utils.extensions

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.darkfox.trainingnotes.dto.Account
import com.google.gson.Gson

infix fun Account.transformToJson(gson: Gson):String = gson.toJson(this)

fun String.restoreUser(gson: Gson) = gson.fromJson<Account>(this)

fun Account.edit(code:Account.()->Unit)  = apply {  code() }

fun SharedPreferences.getAccount(gson: Gson) = getString(ACCOUNT_PREF,"")?.restoreUser(gson)

fun SharedPreferences.removeAccount() = edit {
    putString(ACCOUNT_PREF,null)
}

fun SharedPreferences.storeAccount(account:String){
    edit {
        putString(ACCOUNT_PREF,account)
    }
}

fun SharedPreferences.containsAccount() = contains(ACCOUNT_PREF)

private val ACCOUNT_PREF = "com.example.darkfox.trainingnotes::ACCOUNT"
