package com.example.darkfox.trainingnotes.arch.ui.signIn.signIn.view

import android.os.Bundle
import android.view.View
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes

class SignInFragment : BaseFragment(),ISignInFragment{
    override val layoutId: Int = R.layout.fragment_sign_in

    override val scopeName: String = KoinScopes.SIGNIN_F.scopeName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerListeners()
    }

    override fun registerListeners() {

    }

    companion object {
        const val TAG = "com.example.darkfox.trainingnotes.arch.ui.signIn::SIGNINFTAG"
    }
}