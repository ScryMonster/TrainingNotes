package com.example.darkfox.trainingnotes.arch.ui.root.viewModel

import com.example.darkfox.trainingnotes.arch.base.navigation.SupFragmentNavigator
import com.example.darkfox.trainingnotes.arch.base.ui.ScopedViewModel
import com.example.darkfox.trainingnotes.arch.domain.root.IRootInteractor
import com.example.darkfox.trainingnotes.arch.ui.root.navigation.RootRouter
import com.example.darkfox.trainingnotes.dto.Account

class RootViewModel(private val interactor: IRootInteractor,
                    private val router: RootRouter) : ScopedViewModel(),IRootViewModel{

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