package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.enterUser.EnterUserInteractor
import com.example.darkfox.trainingnotes.arch.domain.enterUser.IEnterUserInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.login.EnterUserFragmentPresenter
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.register.RegisterPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

object EnterUserFragmentsModule {
    val module = module {
            single<IEnterUserInteractor> { EnterUserInteractor() }


            single<EnterUserFragmentContract.Presenter>(named(SignIn)) {
                EnterUserFragmentPresenter(interactor = get())
            }

            single<EnterUserFragmentContract.Presenter>(named(Register)) {
                RegisterPresenter(interactor = get())
            }
    }


    val SignIn = "SignIn"
    val Register = "register"
}