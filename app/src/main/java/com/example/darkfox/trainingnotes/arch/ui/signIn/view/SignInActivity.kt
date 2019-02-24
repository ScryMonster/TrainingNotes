package com.example.darkfox.trainingnotes.arch.ui.signIn.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.ui.signIn.signIn.view.SignInFragment
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.extensions.replaceFragment
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.core.scope.Scope

class SignInActivity : AppCompatActivity(), ISignInActivityView {

    override val scopeName: String = KoinScopes.SIGNIN_A.scopeName
    private lateinit var session:Scope


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        goToSignIn()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyKoinScope()
    }

    override fun buildKoinScope() {
        session = koinContext.createScope(scopeName)
    }

    override fun destroyKoinScope() {
        session.close()
    }

    //region Navigation

    override fun goToSignIn() {
        supportFragmentManager.replaceFragment(SignInFragment(),R.id.sign_in_container,SignInFragment.TAG)
    }

    override fun goToSignUp() {

    }
    //endregion


    //region help functions
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
    //endregion

}