package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchSettingsContract
import com.example.darkfox.trainingnotes.arch.ui.dialogs.searchSettings.SearchSettingsPresenter
import org.koin.dsl.module

object SearchSettingsModule {
    val module = module {
        single<SearchSettingsContract.Presenter> {
            SearchSettingsPresenter()
        }
    }
}