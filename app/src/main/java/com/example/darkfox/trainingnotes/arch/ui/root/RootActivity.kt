package com.example.darkfox.trainingnotes.arch.ui.root

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.NavigationResultView
import com.example.darkfox.trainingnotes.arch.ui.contracts.RootContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserActivity
import com.example.darkfox.trainingnotes.utils.extensions.*
import kotlinx.android.synthetic.main.activity_root.*
import org.koin.android.ext.android.inject
import kotlin.properties.Delegates


class RootActivity : AppCompatActivity(), RootContract.View {

    private val presenter: RootContract.Presenter by inject()
    private var currentNavController: MutableLiveData<NavController>? = null
    private val NAV_BAR_INVISIBLE_SCREENS = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        presenter.attachView(this)
        Log.d("RootInfo", "Created")
        initBottomBarNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    fun returnToEnterUserFlow() {
        startActivity(Intent(this, EnterUserActivity::class.java))
        finish()
    }

    override fun navigateBackWithResult(result: Bundle) {
        val childFragmentManager = supportFragmentManager.findFragmentById(R.id.nav_host_container)?.childFragmentManager
//        var backStackListener: NavController.OnDestinationChangedListener by Delegates.notNull()
//        backStackListener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
//            (childFragmentManager?.primaryNavigationFragment as NavigationResult).onNavigationResult(result)
//            //(supportFragmentManager.findFragmentById(controller.graph.id) as NavigationResult).onNavigationResult(result)
//            currentNavController?.value?.removeOnDestinationChangedListener(backStackListener)
//        }
//        currentNavController?.value?.addOnDestinationChangedListener(backStackListener)//?.addOnBackStackChangedListener(backStackListener)
//
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager.OnBackStackChangedListener {
            (childFragmentManager?.fragments?.get(0) as NavigationResultView).onNavigationResult(result)
            childFragmentManager.removeOnBackStackChangedListener(backStackListener)
        }
        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
        //currentNavController?.value?.navigateUp()
    }

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
        message showInfoInSnackBar rootView
    }

    override fun infoMessage(message: Int) {
        message showInfoInSnackBar rootView
    }

    override fun errorMessage(message: String) {
        message showErrorInSnackBar rootView
    }

    override fun errorMessage(message: Int) {
        message showErrorInSnackBar rootView
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

    private fun initBottomBarNavigation() {

        val graphIds = listOf(R.navigation.trainings_graph, R.navigation.profile_graph)

        val controller = rootBottomNavBar.setupWithNavController(
                navGraphIds = graphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_container,
                intent = intent
        )

        controller.observe(this, Observer { navController ->
            navController?.addOnDestinationChangedListener { controller, destination, arguments ->
                if (NAV_BAR_INVISIBLE_SCREENS.contains(destination.id)) rootBottomNavBar.gone()
                else rootBottomNavBar.visible()
            }
        })
        currentNavController = controller
    }
}





