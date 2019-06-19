package com.example.darkfox.trainingnotes.arch.ui.search

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.search.ISearchInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchContract
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import com.example.darkfox.trainingnotes.utils.enums.SearchType
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

    override fun searchByGroup(groups: List<MuscleGroups>?) {
        uiScope.launch {
            if (groups == null || groups.isEmpty()){
                view?.warningMeaage(SearchType.GROUPS)
            }
            else{
                val byGroups = interactor.searchByGroup(groups)
                view?.setTrainings(byGroups)
            }
        }
    }

    override fun searchByDate(midnight: Long?) {
        uiScope.launch {
            if (midnight == null){
                view?.warningMeaage(SearchType.DATE)
            }
            else{
                val byDate = interactor.searchByDate(midnight)
                view?.setTrainings(byDate)
            }
        }
    }

    override fun clearLastSearchedList() {
        interactor.clearLastSearchedList()
    }
}