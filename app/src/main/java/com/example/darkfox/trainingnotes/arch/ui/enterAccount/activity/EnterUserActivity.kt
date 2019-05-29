package com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity

//import com.example.darkfox.trainingnotes.utils.extensions.replaceFragment
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserContract
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.createScope
import org.koin.core.scope.Scope
import org.koin.standalone.inject

class EnterUserActivity : AppCompatActivity(), EnterUserContract.View {

    override val scopeName: String = KoinScopes.EnterUser.scopeName
    override lateinit var session:Scope
    private val presenter:EnterUserContract.Presenter by inject()
    val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        buildKoinScope()
        bindScope(session)
        presenter.attachView(this)
        goToSignIn()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }


    override fun buildKoinScope() {
        session = createScope(scopeName)
    }

    override fun onSupportNavigateUp(): Boolean {
        return onNavigateUp()
    }

    fun goToRootActivity(account: Account){
        val intent = Intent(this, EnterUserActivity::class.java).apply {
            putExtra(RootActivity.acc_key, account)
        }
        startActivity(intent)

    }


    //region Navigation

    override fun goToSignIn() {
//        supportFragmentManager.replaceFragment(EnterUserFragment(),
//                place = R.id.sign_in_container,
//                tag = EnterUserFragment.TAG)
    }

    override fun goToSignUp() {

    }
    //endregion


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