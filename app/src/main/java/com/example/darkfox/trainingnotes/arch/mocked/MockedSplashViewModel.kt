package com.example.darkfox.trainingnotes.arch.mocked

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.darkfox.trainingnotes.arch.base.ui.ScopedViewModel
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.extensions.withProgressAsync
import com.example.darkfox.trainingnotes.utils.helpers.states.RequestState
import kotlinx.coroutines.launch

class MockedSplashViewModel(private val interactor: ISplashMockedInteractor) : ScopedViewModel() {
    private val accountLiveData = MutableLiveData<Account>()

    val accountData: LiveData<Account> = accountLiveData


    fun attemptRequestPermissions(activity: Activity){
        interactor.attemptRequestPermissions(activity) {
            loadUser()
        }
    }

    fun onRequestPermissionsResult(requestCode: Int,grantResults: IntArray){
        interactor.onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun loadUser() {
        uiScope.launch {
            withProgressAsync {
                interactor.loadUser({ account ->
                    accountLiveData.value = account
                }, { exception ->
                    requestState.value = RequestState.Error(exception)
                })
            }
        }

    }
}