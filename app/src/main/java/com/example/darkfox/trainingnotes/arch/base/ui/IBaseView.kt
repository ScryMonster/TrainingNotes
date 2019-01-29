package com.example.darkfox.trainingnotes.arch.base.ui

interface IBaseView {
    fun infoMessage(message:String)
    fun infoMessage(message:Int)
    fun errorMessage(message:String)
    fun errorMessage(message:Int)
    fun switchOffUiInteraction(flag:Boolean)
}