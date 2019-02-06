package com.example.darkfox.trainingnotes.arch.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.darkfox.trainingnotes.arch.base.ui.ScopedViewModel
import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.extensions.withProgress
import com.example.darkfox.trainingnotes.utils.helpers.states.RequestState

class SplashViewModel(private val interactor: ISplashInteractor) : ScopedViewModel() {

    val accountLiveData = MutableLiveData<Account>()

    fun loadUser() {
        interactor.loadUser({ account ->
            accountLiveData.value = account
        }, { exception ->
            requestState.value = RequestState.Error(exception)
        })

    }
}