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
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.UserProperties
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.WizzardType
import kotlinx.android.synthetic.main.fragment_wizzard.*
import org.koin.standalone.inject

class WizzardFragment : BaseFragment<WizzardContract.View, WizzardContract.Presenter>(), WizzardContract.View {
    override val layoutId: Int = R.layout.fragment_wizzard
    override val presenter: WizzardContract.Presenter by inject()
    override val scopeName: String = KoinScopes.WIZZARD.scopeName
    private var type: WizzardType = WizzardType.NAME
    private var account: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val saveArgs = WizzardFragmentArgs.fromBundle(bundle)
            account = saveArgs.account
            type = saveArgs.type
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillDependsOnWizzardType(type)
        actionButton.setOnClickListener {
            when (type) {
                WizzardType.NAME -> {
                    account?.firstName = firstNameField.text?.toString()
                    account?.lastName = lastNameField.text?.toString()
                    val direction = WizzardFragmentDirections.openPropsWizzard(account, WizzardType.PROPERTIES)
                    findNavController().navigate(direction)
                }
                WizzardType.PROPERTIES -> {
                    account?.properties = UserProperties(age = firstNameField.text?.toString()?.toInt()!!,
                            weight = lastNameField.text?.toString()?.toDouble()!!)
                    presenter.saveAccount(account)
                }
            }
        }
    }

    override fun finishFlow() {
        (activity as EnterUserActivity).goToRootActivity()
    }

    private fun fillDependsOnWizzardType(type: WizzardType) {
        when (type) {
            WizzardType.NAME -> {
                firstNameBox.hint = resources.getString(R.string.first_name)
                lastNameBox.hint = resources.getString(R.string.last_name)
                firstNameField.inputType = InputType.TYPE_CLASS_TEXT
                lastNameField.inputType = InputType.TYPE_CLASS_TEXT
                actionButton.text = resources.getString(R.string.next)
            }
            WizzardType.PROPERTIES -> {
                firstNameBox.hint = resources.getString(R.string.age)
                lastNameBox.hint = resources.getString(R.string.weight)
                firstNameField.inputType = InputType.TYPE_CLASS_NUMBER
                lastNameField.inputType = InputType.TYPE_CLASS_NUMBER
                actionButton.text = resources.getString(R.string.finish)
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val ACCOUNT = "account"
    }

}