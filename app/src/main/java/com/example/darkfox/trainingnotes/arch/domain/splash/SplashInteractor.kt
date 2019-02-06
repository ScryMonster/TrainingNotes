package com.example.darkfox.trainingnotes.arch.domain.splash

import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account

class SplashInteractor(private val localRepository: LocalRepository<Account>) : ISplashInteractor {

    override fun loadUser(success: (Account) -> Unit, error: (Exception) -> Unit) {
        localRepository.restore(success,error)
    }
}