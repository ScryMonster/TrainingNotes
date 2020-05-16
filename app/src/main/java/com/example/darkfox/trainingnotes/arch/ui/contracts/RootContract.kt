package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.NavigalableActivity
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView

interface RootContract {

    interface View : IProgressView,NavigalableActivity {

    }

    interface Presenter : BaseContract.Presenter<RootContract.View> {
    }

}