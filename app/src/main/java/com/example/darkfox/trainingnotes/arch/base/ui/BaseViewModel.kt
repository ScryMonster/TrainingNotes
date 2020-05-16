package com.example.darkfox.trainingnotes.arch.base.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.darkfox.trainingnotes.arch.navigation.Navigator
import com.example.darkfox.trainingnotes.models.dto.Message
import com.google.android.material.snackbar.Snackbar
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent {

    private val navigator: Navigator by inject()

    private val _progressStateHolder = MutableLiveData<Boolean>()
    val progressStateHolder: LiveData<Boolean> = _progressStateHolder

    private val _messageHolder = MutableLiveData<Message>()
    val messageHolder: LiveData<Message> = _messageHolder


    init {
        navigator.setAdditionalActionBeforeNavigation {
            onNavigate()
        }
    }



    private fun onNavigate(){

    }

    fun navigateToId(@IdRes destinationId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
        navigator.navigateToId(destinationId, args, navOptions)
    }

    fun navigateToDirection(direction: NavDirections, navOptions: NavOptions? = null) {
        navigator.navigateToDirection(direction,navOptions)
    }

    fun navigateBack(inclusive: Boolean = false) {
        navigator.navigateBack()
    }

    fun navigateBackTillId(@IdRes destinationId: Int, inclusive: Boolean = false) {
        navigator.navigateBackUntilId(destinationId,inclusive)
    }




    fun showProgress() {
        _progressStateHolder.value = true
    }

    fun hideProgress() {
        _progressStateHolder.value = false
    }




    fun showInfoMessage(message: String, duration: Int = INFO_MASSAGE_DURATION) {
        _messageHolder.value = Message.InfoMessage(message, duration)
    }

    fun showErrorMessage(message: String, duration: Int = ERROR_MASSAGE_DURATION) {
        _messageHolder.value = Message.ErrorMessage(message, duration)
    }

    fun showSuccessMessage(message: String, duration: Int = SUCCESS_MASSAGE_DURATION) {
        _messageHolder.value = Message.SuccessMessage(message, duration)
    }



    companion object {
        private const val INFO_MASSAGE_DURATION = Snackbar.LENGTH_LONG
        private const val ERROR_MASSAGE_DURATION = Snackbar.LENGTH_SHORT
        private const val SUCCESS_MASSAGE_DURATION = Snackbar.LENGTH_SHORT
    }
}