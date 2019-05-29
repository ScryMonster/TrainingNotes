package com.example.darkfox.trainingnotes.arch.domain.wizzard

import com.example.darkfox.trainingnotes.dto.Account

interface IWizzardInteractor {

    fun saveAccount(account: Account,success:(Account)->Unit)

}