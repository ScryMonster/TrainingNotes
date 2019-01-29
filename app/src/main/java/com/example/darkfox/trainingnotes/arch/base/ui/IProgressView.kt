package com.example.darkfox.trainingnotes.arch.base.ui

interface IProgressView:IBaseView {
    fun showProgress(tag: Any?)
    fun hideProgress(tag: Any?)
}