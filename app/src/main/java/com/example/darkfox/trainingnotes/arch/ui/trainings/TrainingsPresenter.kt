package com.example.darkfox.trainingnotes.arch.ui.trainings

import android.util.Log
import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.trainings.ITrainingDayInteractor
import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.arch.ui.contracts.TrainingsContract
import kotlinx.coroutines.launch

class TrainingsPresenter(private val interactor: ITrainingDayInteractor) : BasePresenter<TrainingsContract.View>(),TrainingsContract.Presenter {

    override fun getTrainingDaysById() {
        uiScope.launch {
            val account = interactor.getCurrentUser()
            if (account != null) {
                val days = interactor.getTrainingDaysById(account.fireBaseId)
                view?.setTrainingDays(days)
            }
            else{
                view?.returnToEnterUserFlow()
            }
        }
    }

    override fun createTraining() {

    }
}