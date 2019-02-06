package com.example.darkfox.trainingnotes.arch.domain.splash

import com.example.darkfox.trainingnotes.dto.Account

interface ISplashInteractor {

    fun loadUser(success:(Account)->Unit,error:(Exception)->Unit)
}