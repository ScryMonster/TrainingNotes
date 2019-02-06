package com.example.darkfox.trainingnotes.arch.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.darkfox.trainingnotes.application.TrainingNotes
import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.ui.splash.view.SplashActivity
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import org.koin.core.scope.Scope

abstract class BaseFragment : Fragment(),IBaseView, IKoinView {

    abstract val layoutId : Int
    @IdRes get

    protected val parent get() = activity as SplashActivity?

    protected lateinit var session:Scope

    abstract fun registerListeners()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =  container?.inflate(layoutId)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildKoinScope()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        destroyKoinScope()
    }

    override fun buildKoinScope() {
        session = koinContext.createScope(scopeName)
    }

    override fun destroyKoinScope() {
        session.close()
    }


    override fun switchOffUiInteraction(flag: Boolean) {

        fun blockScreen(){
            parent?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
        fun unBlockScreen(){
            parent?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        if (flag) blockScreen()
        else unBlockScreen()
    }




    override fun infoMessage(message: String) {
        message.showInfoInSnackBar(view!!)
    }

    override fun errorMessage(message: String) {
        message.showErrorInSnackBar(view!!)
    }

    override fun infoMessage(message: Int) {
       resources.getString(message).showInfoInSnackBar(view!!)
    }

    override fun errorMessage(message: Int) {
        resources.getString(message).showErrorInSnackBar(view!!)
    }

}