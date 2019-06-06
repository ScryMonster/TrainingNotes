package com.example.darkfox.trainingnotes.arch.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.ui.contracts.SplashContract
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserActivity
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes.SPLASH
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.createScope
import org.koin.core.scope.Scope
import org.koin.standalone.inject

class SplashActivity : AppCompatActivity(), SplashContract.View {


    override val scopeName: String = SPLASH.scopeName

    override lateinit var session: Scope

    private val presenter:SplashContract.Presenter by inject()

//    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        buildKoinScope()
        bindScope(session)
//        registerListeners()
        presenter.attachView(this)
        presenter.attemptRequestPermissions(this)
    }

//    fun registerListeners() {
//        splashViewModel.accountData.observe(this) { account ->
//            logger.info("Account ${account.firstName} is exist")
//            openRootActivity(account)
//        }
//
//        splashViewModel.requestState.observe(this) { state ->
//            when (state) {
//                is RequestState.Loading_START -> showProgress(null)
//                is RequestState.Loading_STOP -> hideProgress(null)
//                is RequestState.Error -> {
//                    when (state.e) {
//                        is UserNotExist -> {
//                            openLogInActivity()
//                        }
//                    }
//                }
//            }
//        }
//    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.onRequestPermissionsResult(requestCode, grantResults, permissions as Array<String>)
    }

    override fun openRootActivity(account: Account){
        val intent = Intent(this, RootActivity::class.java)
        startActivity(intent)
    }

    override fun openLogInActivity(){
        startActivity(Intent(this, EnterUserActivity::class.java))
    }


    override fun showProgress(tag: Any?) {
        splashRotateLoading.visibility = VISIBLE
        progressBackgroundSplash.visibility = VISIBLE
    }

    override fun hideProgress(tag: Any?) {
        splashRotateLoading.visibility = GONE
        progressBackgroundSplash.visibility = GONE
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


    override fun buildKoinScope() {
        session = createScope(scopeName)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

//    override fun destroyKoinScope() {
//        session.close()
//    }
}
