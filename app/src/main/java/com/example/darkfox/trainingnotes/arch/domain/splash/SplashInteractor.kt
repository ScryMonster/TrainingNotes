package com.example.darkfox.trainingnotes.arch.domain.splash

import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.errors.UserNotExist

class SplashInteractor(private val localRepository: LocalRepository<Account>) : ISplashInteractor {

    override fun loadUser(success: (Account) -> Unit, error: (Exception) -> Unit) {
        localRepository.restore(success){
            if (it is UserNotExist) localRepository.save(Account(1,
                    "nikitots@i.ua",
                    "Nikita",
                    "Totskiy",
                    "ffff"),success = {
                localRepository.restore(success, error)
            })
        }
    }

}