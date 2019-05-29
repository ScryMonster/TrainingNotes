package com.example.darkfox.trainingnotes.arch.base.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BasePresenter<V:BaseContract.View> : BaseContract.Presenter<V> {

    var view:V? = null

    private val parentJob = SupervisorJob()

    protected val uiScope = CoroutineScope(Dispatchers.Main + parentJob)


    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}