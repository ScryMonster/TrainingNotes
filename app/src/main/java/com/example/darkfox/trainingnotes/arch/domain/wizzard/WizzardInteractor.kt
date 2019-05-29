package com.example.darkfox.trainingnotes.arch.domain.wizzard

import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account

class WizzardInteractor(private val localRepository: LocalRepository<Account>) : IWizzardInteractor {
    override fun saveAccount(account: Account, success: (Account) -> Unit) {
        localRepository.restore({ restoredAccount ->
            val acc = restoredAccount
            acc.apply {
                firstName = account.firstName
                lastName = account.lastName
                properties = account.properties
            }

            localRepository.save(acc)
            success(acc)

        },{

        })
    }
}