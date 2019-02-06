package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

interface TokenManager {

    val isTokenExist: Boolean

    fun storeToken(accessToken: String, refreshToken: String)

    fun restoreAccessToken(): String?

    fun restoreRefreshToken(): String
}