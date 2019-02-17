package com.example.darkfox.trainingnotes.arch.ui.root.navigation

import com.example.darkfox.trainingnotes.arch.base.navigation.SupFragmentNavigator
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.Screens

class RootRouter :IRootNavigation{
    private var navigator:SupFragmentNavigator? = null

    fun attachNavigator(navigator: SupFragmentNavigator){
        this.navigator = navigator
    }

    fun detach(){
        navigator = null
    }

    override fun openUserInfoFragment(account: Account) {
        navigator?.goTo(Screens.USER_INFO,account)
    }
}