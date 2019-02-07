package com.example.darkfox.trainingnotes.arch.ui.splash.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.ui.splash.viewmodel.SplashViewModel
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.errors.UserNotExist
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes.SPLASH
import com.example.darkfox.trainingnotes.utils.extensions.observe
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import com.example.darkfox.trainingnotes.utils.helpers.states.RequestState
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class SplashActivity : AppCompatActivity(), ISplashView {

    private lateinit var session: Scope

    private val splashViewModel: SplashViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        buildKoinScope()
        registerListeners()
        splashViewModel.loadUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyKoinScope()
    }

    fun registerListeners() {
        splashViewModel.accountLiveData.observe(this) { account ->
            infoMessage("Account ${account.firstName} is exist")
        }

        splashViewModel.requestState.observe(this) { state ->
            when (state) {
                is RequestState.Loading_START -> showProgress(null)
                is RequestState.Loading_STOP -> hideProgress(null)
                is RequestState.Error -> {
                    when (state.e) {
                        is UserNotExist -> {
                            infoMessage("Need to Log In")
                        }
                    }
                }
            }
        }
    }


    override fun showProgress(tag: Any?) {
        splashRotateLoading.visibility = VISIBLE
    }

    override fun hideProgress(tag: Any?) {
        splashRotateLoading.visibility = GONE
    }

    override fun infoMessage(message: String) {
        message showInfoInSnackBar splash
    }

    override fun infoMessage(message: Int) {
        message showInfoInSnackBar splash
    }

    override fun errorMessage(message: String) {
        message showErrorInSnackBar splash
    }

    override fun errorMessage(message: Int) {
        message showErrorInSnackBar splash
    }

    override fun switchOffUiInteraction(flag: Boolean) {
        fun blockScreen() {
            window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        fun unBlockScreen() {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        if (flag) blockScreen()
        else unBlockScreen()
    }

    override val scopeName: String = SPLASH.scopeName

    override fun buildKoinScope() {
        session = koinContext.createScope(scopeName)
    }

    override fun destroyKoinScope() {
        session.close()
    }

}
