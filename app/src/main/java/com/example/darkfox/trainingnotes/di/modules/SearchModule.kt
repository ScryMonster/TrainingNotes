package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.search.ISearchInteractor
import com.example.darkfox.trainingnotes.arch.domain.search.SearchInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchContract
import com.example.darkfox.trainingnotes.arch.ui.search.SearchPresenter
import org.koin.dsl.module

object SearchModule {
    val module = module {
            single<ISearchInteractor> {
                SearchInteractor()
            }

            single<SearchContract.Presenter> {
                SearchPresenter(get())
            }
    }
}