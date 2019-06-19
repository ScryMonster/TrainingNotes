package com.example.darkfox.trainingnotes.arch.ui.wizzards.names.props

import android.os.Bundle
import android.view.View
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserActivity
import com.example.darkfox.trainingnotes.di.modules.WizzardModule
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.UserProperties
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import kotlinx.android.synthetic.main.fragment_wizzard_properties.*
import org.koin.android.ext.android.inject

class WizzardPropertiesFragment : BaseFragment<WizzardContract.View, WizzardContract.Presenter>(),WizzardContract.View{
    override val layoutId: Int = R.layout.fragment_wizzard_properties
    override val presenter: WizzardContract.Presenter by inject(name = WizzardModule.WIZZARD_PROPS)
    override val scopeName: String = KoinScopes.WIZZARD.scopeName

    private var account:Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val saveArgs = WizzardPropertiesFragmentArgs.fromBundle(bundle)
            account = saveArgs.account
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionButton.setOnClickListener {
            account?.properties = UserProperties(age = tvAge.text?.toString()?.toInt() ?: 0,
                    weight = tvWeight.text?.toString()?.toDouble() ?: 0.0)
            presenter.saveAccount(account)
        }
    }

    override fun finishFlow() {
        (activity as EnterUserActivity).goToRootActivity()
    }

}