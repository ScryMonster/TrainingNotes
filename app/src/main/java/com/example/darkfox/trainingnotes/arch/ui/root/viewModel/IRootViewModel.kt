package com.example.darkfox.trainingnotes.arch.ui.root.viewModel

import com.example.darkfox.trainingnotes.arch.base.navigation.SupFragmentNavigator
import com.example.darkfox.trainingnotes.arch.ui.root.navigation.IRootNavigation

interface IRootViewModel:IRootNavigation {

    fun attachNavigator(navigator: SupFragmentNavigator)
    fun detachNavigator()
}