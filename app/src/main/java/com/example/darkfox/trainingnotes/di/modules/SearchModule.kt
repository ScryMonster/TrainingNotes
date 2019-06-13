package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.search.ISearchInteractor
import com.example.darkfox.trainingnotes.arch.domain.search.SearchInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchContract
import com.example.darkfox.trainingnotes.arch.ui.search.SearchPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object SearchModule {
    private val scopeName = KoinScopes.SEARCH.scopeName

    val module = module {
        scope<ISearchInteractor>(scopeName){
            SearchInteractor()
        }

        scope<SearchContract.Presenter>(scopeName){
            SearchPresenter(get())
        }
    }
}