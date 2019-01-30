package com.example.darkfox.trainingnotes.arch.ui.splash.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes.SPLASH
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.core.scope.Scope

class SplashActivity : AppCompatActivity(),ISplashView {

    private lateinit var session: Scope






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        buildKoinScope()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyKoinScope()
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
