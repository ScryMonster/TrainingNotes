package com.example.darkfox.trainingnotes.arch.ui.wizzards.names

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserActivity
import com.example.darkfox.trainingnotes.di.modules.WizzardModule
import com.example.darkfox.trainingnotes.models.dto.Account
import kotlinx.android.synthetic.main.fragment_wizzard.*
import org.koin.android.ext.android.inject
import org.koin.core.inject
import org.koin.core.qualifier.named

class WizzardNamesFragment : BaseFragment<WizzardContract.View, WizzardContract.Presenter>(), WizzardContract.View {
    override val layoutId: Int = R.layout.fragment_wizzard
    override val presenter: WizzardContract.Presenter by inject(named(WizzardModule.WIZZARD_NAME))
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