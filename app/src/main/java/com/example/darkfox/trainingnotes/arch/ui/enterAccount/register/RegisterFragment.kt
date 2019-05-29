package com.example.darkfox.trainingnotes.arch.ui.enterAccount.register

import android.os.Bundle
import android.view.View
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.RegisterContract
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.standalone.inject

class RegisterFragment : BaseFragment<RegisterContract.View,RegisterContract.Presenter>(),RegisterContract.View {
    override fun showProgress(tag: Any?) {
        rootActivity?.showProgress()
    }

    override fun hideProgress(tag: Any?) {
        rootActivity?.hideProgress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override val layoutId: Int = R.layout.register_fragment
    override val presenter: RegisterContract.Presenter by inject()
    override val scopeName: String = KoinScopes.REGISTER.scopeName



}