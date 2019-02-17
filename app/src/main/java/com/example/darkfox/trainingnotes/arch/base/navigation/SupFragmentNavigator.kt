package com.example.darkfox.trainingnotes.arch.base.navigation

import com.example.darkfox.trainingnotes.utils.enums.Screens

interface SupFragmentNavigator {

    fun goTo(key: Screens, vararg data: Any?)

    fun backTo(key: Screens?, data: Any?)

    fun back()

    fun replace(key: String, data: Any)

    fun systemMessage(message: String)
}