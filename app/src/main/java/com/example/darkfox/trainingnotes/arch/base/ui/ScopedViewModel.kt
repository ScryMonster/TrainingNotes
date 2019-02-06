package com.example.darkfox.trainingnotes.arch.base.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

open class ScopedViewModel : BaseViewModel() {
    private val parentJob = SupervisorJob()

    protected val uiScope = CoroutineScope(Dispatchers.Main + parentJob)


    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }
}