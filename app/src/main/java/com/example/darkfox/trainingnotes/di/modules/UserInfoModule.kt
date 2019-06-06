package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.userInfo.IUserInfoInteractor
import com.example.darkfox.trainingnotes.arch.domain.userInfo.UserInfoInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.UserInfoContract
import com.example.darkfox.trainingnotes.arch.ui.userInfo.view.UserInfoPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object UserInfoModule {

    private val scopeName = KoinScopes.USER_INFO.scopeName

    val module = module {
        scope<IUserInfoInteractor>(scopeName){
            UserInfoInteractor()
        }

        scope<UserInfoContract.Presenter>(scopeName){
            UserInfoPresenter(get())
        }
    }
}