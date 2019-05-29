package com.example.darkfox.trainingnotes.arch.base.ui

interface IProgressView:BaseContract.View {
    fun showProgress(tag: Any? = null)
    fun hideProgress(tag: Any? = null)
}