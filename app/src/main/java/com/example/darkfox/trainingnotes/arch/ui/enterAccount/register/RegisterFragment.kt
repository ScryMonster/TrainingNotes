package com.example.darkfox.trainingnotes.arch.ui.enterAccount.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserActivity
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.WizzardNamesFragment
import com.example.darkfox.trainingnotes.di.modules.EnterUserFragmentsModule
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.EnterUserState
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.WizzardType
import com.example.darkfox.trainingnotes.utils.extensions.afterTextChanged
import com.example.darkfox.trainingnotes.utils.extensions.disable
import com.example.darkfox.trainingnotes.utils.extensions.enable
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment<EnterUserFragmentContract.View,EnterUserFragmentContract.Presenter>(),EnterUserFragmentContract.View {
    override val layoutId: Int = R.layout.fragment_register
    override val presenter: EnterUserFragmentContract.Presenter by inject(name = EnterUserFragmentsModule.Register)
    override val scopeName: String = KoinScopes.ENTER_USER_FRAGMENTS.scopeName

    private var state = EnterUserState()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        state
                .setSuccessListener {
                    singInBtn.enable()
                }
                .setErrorListener {
                    singInBtn.disable()
                }
        initListeners()
    }

    override fun navigateToWizzardFlow(account: Account) {
        val args = Bundle().apply {
            putSerializable(WizzardNamesFragment.TYPE, WizzardType.NAME)
            putParcelable(WizzardNamesFragment.ACCOUNT, account)
        }
        findNavController().navigate(R.id.wizzards_graph, args)
    }

    override fun setEmailState(state: Boolean, message: String?) {
        this.state.emailState = state
        this.state.checkState()
        if (state) emailBox.error = ""
        else emailBox.error = message ?: ""
    }

    override fun setPasswordState(state: Boolean, message: String?) {
        this.state.passwordState = state
        this.state.checkState()
        if (state) passwordBox.error = ""
        else passwordBox.error = message ?: ""
    }

    override fun navigateToMainFlow() {}

    override fun showProgress(tag: Any?) {
        (activity as EnterUserActivity).showProgress()
    }

    override fun hideProgress(tag: Any?) {
        (activity as EnterUserActivity).hideProgress()
    }

    private fun initListeners() {
        emailField.afterTextChanged { email ->
            presenter.checkEmail(email)
        }

        passwordField.afterTextChanged { password ->
            presenter.checkPassword(password)
        }

        singInBtn.setOnClickListener {
            presenter.doStaff(emailField.text?.toString(), passwordField.text.toString())
        }

        tvSignIn.setOnClickListener {
            findNavController().navigateUp()
        }

    }

}