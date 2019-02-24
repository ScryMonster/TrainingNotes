package com.example.darkfox.trainingnotes.arch.ui.root.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.navigation.SupFragmentNavigator
import com.example.darkfox.trainingnotes.arch.ui.root.navigation.IRootNavigation
import com.example.darkfox.trainingnotes.arch.ui.root.viewModel.RootViewModel
import com.example.darkfox.trainingnotes.arch.ui.userInfo.view.UserInfoFragment
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.Screens.*
import com.example.darkfox.trainingnotes.utils.enums.Screens
import com.example.darkfox.trainingnotes.utils.extensions.addFragment
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope


class RootActivity : AppCompatActivity(), IRootView, IRootNavigation {

    private lateinit var session: Scope
    private val rootViewModel: RootViewModel by viewModel()

    private val navigator by lazy {
        object : SupFragmentNavigator {
            override fun goTo(key: Screens, vararg data: Any?) {
                when (key) {
                    USER_INFO -> supportFragmentManager.addFragment(UserInfoFragment.newInstance(data[0] as Account), tag = key.toString())
                    else -> {
                    }
                }
            }

            override fun backTo(key: Screens?, data: Any?) {
                val count = supportFragmentManager.backStackEntryCount
                if (count > 0) {
                    if (key != null) {
                        supportFragmentManager.popBackStack(key.toString(), 0)
                    } else while (count > 0) {
                        supportFragmentManager.popBackStack()
                    }
                }
            }

            override fun back() {
                supportFragmentManager.popBackStack()
            }

            override fun replace(key: String, data: Any) {}

            override fun systemMessage(message: String) {
                infoMessage(message)
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0){
            navigator.back()
        } else finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        buildKoinScope()
        rootViewModel.attachNavigator(navigator)
        openUserInfoFragment(intent.getParcelableExtra(acc_key) as Account)
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyKoinScope()
        rootViewModel.detachNavigator()
    }


    //region DI Scope Initialization
    override val scopeName: String = KoinScopes.ROOT_ACT.scopeName

    override fun buildKoinScope() {
        session = koinContext.createScope(scopeName)
    }

    override fun destroyKoinScope() {
        session.close()
    }
    //endregion

    //region Progress execution functions
    override fun showProgress(tag: Any?) {
        progressBackground.visibility = VISIBLE
        rootRotateLoading.visibility = VISIBLE
    }

    override fun hideProgress(tag: Any?) {
        progressBackground.visibility = GONE
        rootRotateLoading.visibility = GONE
    }
    //endregion

    //region help functions
    override fun infoMessage(message: String) {
        message showInfoInSnackBar splash
    }

    override fun infoMessage(message: Int) {
        message showInfoInSnackBar splash
    }

    override fun errorMessage(message: String) {
        message showErrorInSnackBar splash
    }

    override fun errorMessage(message: Int) {
        message showErrorInSnackBar splash
    }

    override fun switchOffUiInteraction(flag: Boolean) {
        fun blockScreen() {
            window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        fun unBlockScreen() {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        if (flag) blockScreen()
        else unBlockScreen()
    }
    //endregion

    override fun openUserInfoFragment(account: Account) {
        rootViewModel.openUserInfoFragment(account)
    }

    companion object {
        const val acc_key = "com.example.darkfox.trainingnotes.arch.ui.root.view::Account_Key"
    }

}