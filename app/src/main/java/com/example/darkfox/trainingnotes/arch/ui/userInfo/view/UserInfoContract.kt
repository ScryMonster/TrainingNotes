package com.example.darkfox.trainingnotes.arch.ui.userInfo.view

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView

interface UserInfoContract {

    interface View: IProgressView{

    }

    interface Presenter : BaseContract.Presenter<UserInfoContract.View>{

    }
}