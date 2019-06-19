package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchSettingsContract
import com.example.darkfox.trainingnotes.arch.ui.dialogs.searchSettings.SearchSettingsPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object SearchSettingsModule {
    private val scopeName = KoinScopes.SEARCH_SETTINGS.scopeName

    val module = module {
        scope<SearchSettingsContract.Presenter>(scopeName){
            SearchSettingsPresenter()
        }
    }
}