package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

interface ITokenManager {

    val isTokenExist: Boolean

    fun storeToken(accessToken: String, refreshToken: String)

    fun restoreAccessToken(): String?

    fun restoreRefreshToken(): String?

    fun cleanTokens()
}