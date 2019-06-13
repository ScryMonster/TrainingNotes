package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract

interface SearchSettingsContract {
    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}