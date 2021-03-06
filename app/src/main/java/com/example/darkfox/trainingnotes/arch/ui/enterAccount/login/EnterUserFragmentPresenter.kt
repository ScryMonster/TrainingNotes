package com.example.darkfox.trainingnotes.arch.ui.enterAccount.login

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.enterUser.IEnterUserInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.EnterUserFlow
import com.example.darkfox.trainingnotes.utils.extensions.withProgress
import com.example.darkfox.trainingnotes.utils.extensions.withProgressAsync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EnterUserFragmentPresenter(private val interactor: IEnterUserInteractor) : BasePresenter<EnterUserFragmentContract.View>(),
        EnterUserFragmentContract.Presenter {

    private var job: Job? = null
//    private var flow = EnterUserFlow.LOGIN


    override fun checkPassword(password: String) {
        job?.cancel()
        job = uiScope.launch(Dispatchers.Main) {
            delay(400)
            interactor.checkPassword(password,
                    {
                        view?.setPasswordState(true)
                    },
                    { cause ->
                        view?.setPasswordState(false, cause)
                    })
        }
    }

    override fun checkEmail(email: String) {
        job?.cancel()
        job = uiScope.launch(Dispatchers.Main) {
            delay(400)
            interactor.checkEmail(email,
                    {
                        view?.setEmailState(true)
                    },
                    { cause ->
                        view?.setEmailState(false, cause)
                    })
        }
    }

    override fun doStaff(email: String?, password: String?) {
        uiScope.launch {
            withProgressAsync {
                interactor.signIn(email, password,
                        { account ->
                            view?.navigateToMainFlow()
                        },
                        {
                            view?.errorMessage(it.localizedMessage)
                        })
            }
        }
    }

//
//    override fun setFlow(flow: EnterUserFlow) {
//        this.flow = flow
//    }


}