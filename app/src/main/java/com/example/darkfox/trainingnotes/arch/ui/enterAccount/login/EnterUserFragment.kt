package com.example.darkfox.trainingnotes.arch.ui.enterAccount.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.WizzardFragment
import com.example.darkfox.trainingnotes.dto.EnterUserState
import com.example.darkfox.trainingnotes.utils.enums.EnterUserFlow
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.WizzardType
import com.example.darkfox.trainingnotes.utils.enums.convertToEnterUserFlow
import com.example.darkfox.trainingnotes.utils.extensions.afterTextChanged
import com.example.darkfox.trainingnotes.utils.extensions.disable
import com.example.darkfox.trainingnotes.utils.extensions.enable
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.standalone.inject

class EnterUserFragment : BaseFragment<EnterUserFragmentContract.View, EnterUserFragmentContract.Presenter>(), EnterUserFragmentContract.View {

    override val layoutId: Int = R.layout.fragment_sign_in

    override val scopeName: String = KoinScopes.LOG_IN.scopeName
    override val presenter: EnterUserFragmentContract.Presenter  by inject()

    private var state = EnterUserState()
    private var flow = EnterUserFlow.LOGIN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val args = EnterUserFragmentArgs.fromBundle(bundle)
            flow = args.flow?.convertToEnterUserFlow() ?: EnterUserFlow.LOGIN
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        state
                .setSuccessListener {
                    singInBtn.enable()
                }
                .setErrorListener {
                    singInBtn.disable()
                }
        presenter.setFlow(flow)
        fillViewDependsOnFlow(flow)
        initListeners()
    }


    override fun setPasswordState(state: Boolean, message: String?) {
        this.state.passwordState = state
        this.state.checkState()
        if (state) passwordBox.error = ""
        else passwordBox.error = message ?: ""
    }

    override fun setEmailState(state: Boolean, message: String?) {
        this.state.emailState = state
        this.state.checkState()
        if (state) emailBox.error = ""
        else emailBox.error = message ?: ""
    }

    private fun initListeners() {
        emailField.afterTextChanged { email ->
            presenter.checkEmail(email)
        }

        passwordField.afterTextChanged { password ->
            presenter.checkPassword(password)
        }

        singInBtn.setOnClickListener {
            presenter.doStaff(emailField.text?.toString(), passwordField.text.toString(),
                    { account ->
                        if (flow == EnterUserFlow.LOGIN) {
                        } else {
                            val args = Bundle().apply {
                                putSerializable(WizzardFragment.TYPE, WizzardType.NAME)
                                putParcelable(WizzardFragment.ACCOUNT, account)
                            }
                            findNavController().navigate(R.id.wizzards_graph, args)
                        }
                    },
                    {

                    })
        }

        registerTV.setOnClickListener {
            //            flow = if (flow == EnterUserFlow.LOGIN) EnterUserFlow.REGISTER
//            else EnterUserFlow.LOGIN
//            presenter.setFlow(flow)
//            fillViewDependsOnFlow(flow)
            if (flow == EnterUserFlow.LOGIN) {
                val direction = EnterUserFragmentDirections.openRegister(EnterUserFlow.REGISTER.name)
                findNavController().navigate(direction)
            }
            else findNavController().navigateUp()
        }

    }

    private fun fillViewDependsOnFlow(flow: EnterUserFlow) {
        if (flow == EnterUserFlow.LOGIN) {
            singInBtn.text = resources.getString(R.string.sign_In)
            registerTV.text = resources.getString(R.string.register)
        } else {
            singInBtn.text = resources.getString(R.string.register)
            registerTV.text = resources.getString(R.string.sign_In)
        }
    }

    companion object {
        const val TAG = "com.example.darkfox.trainingnotes.arch.ui.signIn::SIGNINFTAG"
    }
}


