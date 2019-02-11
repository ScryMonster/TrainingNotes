package com.example.darkfox.trainingnotes.arch.ui.splash.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.darkfox.trainingnotes.arch.base.ui.ScopedViewModel
import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.extensions.withProgress
import com.example.darkfox.trainingnotes.utils.helpers.states.RequestState
import org.koin.standalone.KoinComponent

class SplashViewModel(private val interactor: ISplashInteractor) : ScopedViewModel(),KoinComponent {

    val accountLiveData = MutableLiveData<Account>()


    fun attemptRequestPermissions(activity: Activity){
        interactor.attemptRequestPermissions(activity){
            loadUser()
        }
    }

    fun onRequestPermissionsResult(requestCode: Int,grantResults: IntArray){
        interactor.onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun loadUser() {
            withProgress {
                interactor.loadUser({ account ->
                    accountLiveData.value = account
                }, { exception ->
                    requestState.value = RequestState.Error(exception)
                })
            }

    }
}