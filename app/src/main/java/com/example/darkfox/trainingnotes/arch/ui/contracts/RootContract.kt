package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.NavigalableActivity
import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.dto.Account

interface RootContract {

    interface View : IProgressView, IKoinView,NavigalableActivity {

    }

    interface Presenter : BaseContract.Presenter<RootContract.View> {
    }

}