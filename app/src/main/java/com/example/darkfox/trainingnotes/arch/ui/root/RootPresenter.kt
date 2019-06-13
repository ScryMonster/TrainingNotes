package com.example.darkfox.trainingnotes.arch.ui.root

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.root.IRootInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.RootContract
import com.example.darkfox.trainingnotes.dto.Account

class RootPresenter(private val interactor: IRootInteractor) : BasePresenter<RootContract.View>(),RootContract.Presenter {


}