package com.example.darkfox.trainingnotes.arch.ui.root.navigation

import com.example.darkfox.trainingnotes.dto.Account

interface IRootNavigation {

    fun openUserInfoFragment(account:Account)
}