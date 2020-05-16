package com.example.darkfox.trainingnotes.arch.ui.trainings

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.trainings.ITrainingDayInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.TrainingsContract
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.extensions.withProgressAsync
import kotlinx.coroutines.launch

class TrainingsPresenter(private val interactor: ITrainingDayInteractor) : BasePresenter<TrainingsContract.View>(),TrainingsContract.Presenter {

    override fun getTrainingDaysById() {
        uiScope.launch {
            withProgressAsync {
                val account = interactor.getCurrentUser()
                if (account != null) {
                    val days = interactor.getTrainingDaysById(account.fireBaseId)
                    view?.setTrainingDays(days)
                } else {
                    view?.returnToEnterUserFlow()
                }
            }
        }
    }

    override fun deleteTraining(day: TrainingDay) {
        uiScope.launch {
            withProgressAsync {
                interactor.deleteTraining(day){
                    getTrainingDaysById()
                }
            }
        }
    }

    override fun removeTraining(day: TrainingDay) {
        uiScope.launch {
            withProgressAsync {
                interactor.removeTraining(day)
            }
        }
    }
}