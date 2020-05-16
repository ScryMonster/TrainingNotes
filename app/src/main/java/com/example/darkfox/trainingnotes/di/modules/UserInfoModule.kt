package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.userInfo.IUserInfoInteractor
import com.example.darkfox.trainingnotes.arch.domain.userInfo.UserInfoInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.UserInfoContract
import com.example.darkfox.trainingnotes.arch.ui.userInfo.view.UserInfoPresenter
import org.koin.dsl.module

object UserInfoModule {
    val module = module {
        single<IUserInfoInteractor> {
            UserInfoInteractor()
        }

        single<UserInfoContract.Presenter> {
            UserInfoPresenter(get())
        }
    }
}