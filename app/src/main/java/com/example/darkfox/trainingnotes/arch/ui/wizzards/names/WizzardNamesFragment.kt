package com.example.darkfox.trainingnotes.arch.ui.wizzards.names

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.RootContract
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserActivity
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.di.modules.WizzardModule
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.UserProperties
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.WizzardType
import kotlinx.android.synthetic.main.fragment_wizzard.*
import org.koin.standalone.inject

class WizzardNamesFragment : BaseFragment<WizzardContract.View, WizzardContract.Presenter>(), WizzardContract.View {
    override val layoutId: Int = R.layout.fragment_wizzard
    override val presenter: WizzardContract.Presenter by inject(WizzardModule.WIZZARD_NAME)
    override val scopeName: String = KoinScopes.WIZZARD.scopeName
    private var account: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val saveArgs = WizzardNamesFragmentArgs.fromBundle(bundle)
            account = saveArgs.account
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        fillDependsOnWizzardType(type)
        actionButton.setOnClickListener {
            account?.firstName = firstNameField.text?.toString() ?: ""
            account?.lastName = lastNameField.text?.toString() ?: ""
            val direction = WizzardNamesFragmentDirections.openWizzardProperties(account)
            findNavController().navigate(direction)
        }

        ivSkip.setOnClickListener {
            (activity as EnterUserActivity).closeWizzardScreens()
        }
    }


    override fun finishFlow() {
        (activity as EnterUserActivity).goToRootActivity()
    }

    companion object {
        const val TYPE = "type"
        const val ACCOUNT = "account"
    }

}