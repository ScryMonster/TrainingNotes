package com.example.darkfox.trainingnotes.arch.ui.root

import com.example.darkfox.trainingnotes.arch.base.navigation.SupFragmentNavigator
import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.root.IRootInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.RootContract
import com.example.darkfox.trainingnotes.arch.ui.root.navigation.RootRouter
import com.example.darkfox.trainingnotes.dto.Account

class RootPresenter(private val interactor: IRootInteractor,
                    private val router: RootRouter) : BasePresenter<RootContract.View>(),RootContract.Presenter {

    override fun attachNavigator(navigator: SupFragmentNavigator) {
        router.attachNavigator(navigator)
    }

    override fun detachNavigator() {
        router.detach()
    }

    override fun openUserInfoFragment(account: Account) {
        router.openUserInfoFragment(account)
    }
}