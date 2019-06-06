package com.example.darkfox.trainingnotes.arch.ui.userInfo.view

import android.os.Bundle
import android.view.View
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.UserInfoContract
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import kotlinx.android.synthetic.main.fragment_user_info.*
import org.koin.standalone.inject

class UserInfoFragment : BaseFragment<UserInfoContract.View, UserInfoContract.Presenter>(), UserInfoContract.View {
    override val presenter: UserInfoContract.Presenter by inject()

    override val layoutId: Int = R.layout.fragment_user_info

    override val scopeName: String = KoinScopes.USER_INFO.scopeName


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getCurrentUser()
    }


    private fun initRV() {

    }

    override fun fillViewWithUser(account: Account) {
        userName.text = account?.firstName ?: ""
        userSurname.text = account?.lastName ?: ""
        userEmail.text = account.email ?: ""
    }

    override fun returnToEnterUserFlow() {
        (activity as RootActivity).returnToEnterUserFlow()
    }

    override fun showProgress(tag: Any?) {
        (activity as RootActivity).showProgress()
    }

    override fun hideProgress(tag: Any?) {
        (activity as RootActivity).hideProgress()
    }


    companion object {
        private const val key_acc = "com.example.darkfox.trainingnotes.arch.ui.userInfo.view::ACCOUNT_KEY"

        fun newInstance(account: Account) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(key_acc, account)
            }
        }
    }
}