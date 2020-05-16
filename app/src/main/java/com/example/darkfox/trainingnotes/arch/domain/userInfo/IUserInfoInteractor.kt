package com.example.darkfox.trainingnotes.arch.domain.userInfo

import com.example.darkfox.trainingnotes.models.dto.Account

interface IUserInfoInteractor {
    fun getCurrentUser(success:(Account)->Unit, fail:(Exception)->Unit)
}