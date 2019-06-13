package com.example.darkfox.trainingnotes.arch.ui.search

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.search.ISearchInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchPresenter(private val interactor:ISearchInteractor) : BasePresenter<SearchContract.View>(),SearchContract.Presenter {

    private var job: Job? = null

    override fun searchByName(name: String) {
        job?.cancel()
        job = uiScope.launch {
            delay(300)
            view?.setClearBtnVisibility(name.isNotEmpty())
            val byName = interactor.searchByName(name)
            view?.setTrainings(byName)
        }
    }

    override fun clearLastSearchedList() {
        interactor.clearLastSearchedList()
    }
}