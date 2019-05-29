//package com.example.darkfox.trainingnotes.arch.ui.signIn.login.viewModel
//
//import androidx.lifecycle.MutableLiveData
//import com.example.darkfox.trainingnotes.arch.base.ui.ScopedViewModel
//import com.example.darkfox.trainingnotes.arch.domain.singIn.IEnterUserInteractor
//import com.example.darkfox.trainingnotes.utils.enums.LogInStates
//import kotlinx.coroutines.*
//
//class SignInFragmentViewModel(private val interactor: IEnterUserInteractor) : ScopedViewModel(), ISignInFragmentViewModel {
//
//    val emailState = MutableLiveData<LogInStates>()
//    val passwordState = MutableLiveData<LogInStates>()
//    private var job: Job? = null
//
//    override fun checkEmail(email: String) {
//        job?.cancel()
//        job = GlobalScope.launch(Dispatchers.Main) {
//            delay(400)
//            interactor.checkEmail(email,
//                    {
//                        emailState.value = LogInStates.SUCCESS_EMAIL
//                    },
//                    {
//                        emailState.value = LogInStates.ERROR_EMAIL
//                    })
//        }
//    }
//
//    override fun checkPassword(password: String) {
//        job?.cancel()
//        job = GlobalScope.launch(Dispatchers.Main) {
//            delay(400)
//            interactor.checkPassword(password,
//                    {
//                        passwordState.value = LogInStates.SUCCESS_PASSWORD
//                    },
//                    {
//                        passwordState.value = LogInStates.ERROR_PASSWORD
//                    })
//        }
//    }
//
//    override fun signIn(email: String?, password: String?, success: () -> Unit, error: () -> Unit) {
//        interactor.signIn(email, password, success, error)
//    }
//
//
//}