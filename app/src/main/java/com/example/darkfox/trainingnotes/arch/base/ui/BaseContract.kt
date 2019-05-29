package com.example.darkfox.trainingnotes.arch.base.ui

interface BaseContract {

    interface View {
        fun infoMessage(message:String)
        fun infoMessage(message:Int)
        fun errorMessage(message:String)
        fun errorMessage(message:Int)
        fun switchOffUiInteraction(flag:Boolean)
    }

    interface Presenter<V : BaseContract.View> {
        fun attachView(view: V)
        fun detachView()
    }
}