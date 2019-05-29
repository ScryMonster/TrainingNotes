package com.example.darkfox.trainingnotes.arch.ui.root

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.ui.contracts.RootContract
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.createScope
import org.koin.androidx.scope.ext.android.getOrCreateScope
import org.koin.core.scope.Scope
import org.koin.standalone.inject


class RootActivity : AppCompatActivity(), RootContract.View  {

    override val scopeName: String = KoinScopes.ROOT_ACT.scopeName
    override lateinit var session: Scope
    private val presenter:RootContract.Presenter by inject()
    val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }
    //region Old Navigation
//    private val navigator by lazy {
//        object : SupFragmentNavigator {
//            override fun goTo(key: Screens, vararg data: Any?) {
//                when (key) {
//                    USER_INFO -> supportFragmentManager.addFragment(UserInfoFragment.newInstance(data[0] as Account), tag = key.toString())
//                    else -> {
//                    }
//                }
//            }
//
//            override fun backTo(key: Screens?, data: Any?) {
//                val count = supportFragmentManager.backStackEntryCount
//                if (count > 0) {
//                    if (key != null) {
//                        supportFragmentManager.popBackStack(key.toString(), 0)
//                    } else while (count > 0) {
//                        supportFragmentManager.popBackStack()
//                    }
//                }
//            }
//
//            override fun back() {
//                supportFragmentManager.popBackStack()
//            }
//
//            override fun replace(key: String, data: Any) {}
//
//            override fun systemMessage(message: String) {
//                infoMessage(message)
//            }
//
//        }
//    }
    //endregion

    override fun onBackPressed() {
        super.onBackPressed()
        navController.navigateUp()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        buildKoinScope()
        bindScope(session)
        presenter.attachView(this)
        Log.d("RootInfo","Created")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachNavigator()
        presenter.detachView()
    }

    override fun onSupportNavigateUp(): Boolean {
        return onNavigateUp()
    }


    //region DI Scope Initialization

    override fun buildKoinScope() {
        session = getOrCreateScope(scopeName)
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

    private fun initNavigation(){

    }

    override fun openUserInfoFragment(account: Account) {
        presenter.openUserInfoFragment(account)
    }

    companion object {
        const val acc_key = "com.example.darkfox.trainingnotes.arch.ui.root.view::Account_Key"
    }

}