package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract

interface AddWarmUpContract {

    interface View : BaseContract.View,IKoinView{

    }

    interface Presenter : BaseContract.Presenter<View>{

    }
}