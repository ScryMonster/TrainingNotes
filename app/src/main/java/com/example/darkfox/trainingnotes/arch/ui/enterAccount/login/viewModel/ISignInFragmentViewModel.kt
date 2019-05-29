package com.example.darkfox.trainingnotes.arch.ui.enterAccount.login.viewModel

interface ISignInFragmentViewModel{

    fun checkEmail(email:String)
    fun checkPassword(password:String)
    fun signIn(email: String?, password: String?, success: () -> Unit, error: () -> Unit)
}