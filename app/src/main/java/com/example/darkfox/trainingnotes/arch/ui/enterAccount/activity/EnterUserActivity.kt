package com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserContract
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.WizzardNamesFragment
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.android.ext.android.inject

class EnterUserActivity : AppCompatActivity(), EnterUserContract.View {

    private val presenter: EnterUserContract.Presenter by inject()
    val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun goToRootActivity() {
        val intent = Intent(this, RootActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showProgress() {
        progressBackground.visibility = VISIBLE
        rootRotateLoading.visibility = VISIBLE
    }

    override fun hideProgress() {
        progressBackground.visibility = GONE
        rootRotateLoading.visibility = GONE
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        Log.d("BackPressed", "trtr")
        val childFragmentManager = supportFragmentManager.fragments[0]?.childFragmentManager
        if (childFragmentManager?.fragments?.get(0) is WizzardNamesFragment) closeWizzardScreens()

    }

    override fun closeWizzardScreens() {
        presenter.saveEmptyProperties()
    }


    //region help functions
    override fun infoMessage(message: String) {
        message showInfoInSnackBar enter_view
    }

    override fun infoMessage(message: Int) {
        message showInfoInSnackBar enter_view
    }

    override fun errorMessage(message: String) {
        message showErrorInSnackBar enter_view
    }

    override fun errorMessage(message: Int) {
        message showErrorInSnackBar enter_view
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

}