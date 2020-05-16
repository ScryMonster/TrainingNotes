package com.example.darkfox.trainingnotes.arch.domain.userInfo

import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.errors.UserNotExist

class UserInfoInteractor : IUserInfoInteractor {

    override fun getCurrentUser(success: (Account) -> Unit, fail: (Exception) -> Unit) {
        val currentAccount = DataProvider.getCurrentAccount()
        if (currentAccount != null) success(currentAccount)
        else fail(UserNotExist())
    }
}