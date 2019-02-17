package com.example.darkfox.trainingnotes.arch.ui.userInfo.view

import android.os.Bundle
import android.view.View
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment : BaseFragment(),IUserInfoView {

    override val layoutId: Int = R.layout.fragment_user_info

    override val scopeName: String = KoinScopes.USER_INFO.scopeName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val account = arguments?.getParcelable<Account>(key_acc)!!
        userName.setValue(account.firstName)
        userSurname.setValue(account.lastName)
        userEmail.setValue(account.email)
    }

    override fun showProgress(tag: Any?) {

    }

    override fun hideProgress(tag: Any?) {

    }

    override fun registerListeners() {

    }



    companion object {
        private const val key_acc = "com.example.darkfox.trainingnotes.arch.ui.userInfo.view::ACCOUNT_KEY"

        fun newInstance(account: Account) = UserInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(key_acc,account)
            }
        }
    }
}