package com.example.darkfox.trainingnotes.arch.ui.splash

import android.app.Activity
import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SplashContract
import com.example.darkfox.trainingnotes.utils.extensions.withProgress
import com.example.darkfox.trainingnotes.utils.extensions.withProgressAsync
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashPresenter(private val interactor: ISplashInteractor) : BasePresenter<SplashContract.View>(),SplashContract.Presenter {
   override fun attemptRequestPermissions(activity: Activity){
        interactor.attemptRequestPermissions(activity){
            loadUser()
        }
    }

   override fun onRequestPermissionsResult(requestCode: Int,grantResults: IntArray,permissions:Array<String>){
        interactor.onRequestPermissionsResult(requestCode, grantResults,permissions)
    }

    private fun loadUser() {

            withProgress {
                interactor.loadUser({ account ->
                    view?.openRootActivity(account)
                }, { exception ->
                    view?.openLogInActivity()
                })
            }
    }
}