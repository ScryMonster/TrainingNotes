package com.example.darkfox.trainingnotes.arch.domain.splash

import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.errors.UserNotExist

class SplashInteractor(private val localRepository: LocalRepository<Account>) : ISplashInteractor {

    override fun loadUser(success: (Account) -> Unit, error: (Exception) -> Unit) {
        localRepository.restore(success){
            checkError(it,success,error)
        }
    }

    fun checkError(error: Exception,success: (Account) -> Unit, errorFun: (Exception) -> Unit){
        if (error is UserNotExist) localRepository.save(mockAccount(),success = {
            localRepository.restore(success, errorFun)
        })
    }

    private fun mockAccount() = Account(1,
            "nikitots@i.ua",
            "Nikita",
            "Totskiy",
            "ffff")

}