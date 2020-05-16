package com.example.darkfox.trainingnotes.arch.ui.createTraining

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.newTraining.ICreateTrainingInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.CreateTrainingContract
import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.utils.extensions.withProgressAsync
import kotlinx.coroutines.launch

class CreateTrainingPresenter(private val interactor: ICreateTrainingInteractor) : BasePresenter<CreateTrainingContract.View>(), CreateTrainingContract.Presenter {


    private var account: Account? = null

    override fun saveTraining(dayHolder: TrainingDayHolder) {
        uiScope.launch {
            withProgressAsync {
                account = interactor.getCurrentUser()
                account?.let {
                    interactor.saveTrraining(dayHolder, it.fireBaseId,
                            {
                                view?.goBack()
                            },
                            {
                                view?.errorMessage(it.localizedMessage)
                            })
                } ?: view?.returnToEnterUserFlow()
            }
        }
    }

}