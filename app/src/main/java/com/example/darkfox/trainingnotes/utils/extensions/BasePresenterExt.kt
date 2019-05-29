package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView


fun <V:IProgressView> BasePresenter<V>.withProgress(code:()->Unit) {
    view?.showProgress()
    code.invoke()
    view?.hideProgress()
}

suspend fun <V:IProgressView> BasePresenter<V>.withProgressAsync(code:suspend ()->Unit) {
    view?.showProgress()
    code.invoke()
    view?.hideProgress()
}