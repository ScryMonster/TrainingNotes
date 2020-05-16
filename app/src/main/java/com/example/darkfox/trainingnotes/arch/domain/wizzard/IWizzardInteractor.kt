package com.example.darkfox.trainingnotes.arch.domain.wizzard

import com.example.darkfox.trainingnotes.models.dto.Account

interface IWizzardInteractor {

    suspend fun saveAccount(account: Account, success: () -> Unit, fail: (Exception) -> Unit)
}