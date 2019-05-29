package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.base.navigation.SupFragmentNavigator
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.arch.ui.root.navigation.IRootNavigation
import com.example.darkfox.trainingnotes.dto.Account

interface RootContract {

    interface View : IProgressView, IKoinView, IRootNavigation {

    }

    interface Presenter : BaseContract.Presenter<RootContract.View> {
        fun attachNavigator(navigator: SupFragmentNavigator)
        fun detachNavigator()
        fun openUserInfoFragment(account: Account)
    }

}