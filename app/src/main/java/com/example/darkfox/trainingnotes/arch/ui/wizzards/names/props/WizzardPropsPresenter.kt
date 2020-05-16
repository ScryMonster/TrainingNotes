package com.example.darkfox.trainingnotes.arch.ui.wizzards.names.props

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.wizzard.IWizzardInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.models.dto.Account
import kotlinx.coroutines.launch

class WizzardPropsPresenter(private val interactor: IWizzardInteractor) : BasePresenter<WizzardContract.View>(), WizzardContract.Presenter {
    override fun saveAccount(account: Account?) {
        uiScope.launch {
            account?.let { notNullAccount ->
                interactor.saveAccount(notNullAccount,
                        {
                            view?.finishFlow()
                        },
                        {
                            view?.errorMessage(it.message ?: it.localizedMessage)
                        })
            }

        }
    }
}