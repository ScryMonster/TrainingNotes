package com.example.darkfox.trainingnotes.arch.ui.userInfo.view

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.userInfo.IUserInfoInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.UserInfoContract

class UserInfoPresenter(private val interactor: IUserInfoInteractor) : BasePresenter<UserInfoContract.View>(), UserInfoContract.Presenter {

    override fun getCurrentUser() {
        interactor.getCurrentUser({account->
            view?.fillViewWithUser(account)
        },{
            view?.returnToEnterUserFlow()
        })
    }
}