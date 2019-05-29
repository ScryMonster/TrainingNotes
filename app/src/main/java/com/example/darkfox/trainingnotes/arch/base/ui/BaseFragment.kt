package com.example.darkfox.trainingnotes.arch.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import com.example.darkfox.trainingnotes.utils.extensions.showErrorInSnackBar
import com.example.darkfox.trainingnotes.utils.extensions.showInfoInSnackBar
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.createScope
import org.koin.androidx.scope.ext.android.getOrCreateScope
import org.koin.core.scope.Scope

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(), BaseContract.View, IKoinView {

    abstract val layoutId: Int
        @IdRes get

    abstract val presenter: P

    override lateinit var session: Scope

    val rootActivity: RootActivity?
        get() {
            return if (activity is RootActivity) activity as RootActivity
            else null
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(layoutId)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildKoinScope()
        bindScope(session)
        presenter.attachView(this as V)
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun buildKoinScope() {
        session = getOrCreateScope(scopeName)
    }

//    override fun destroyKoinScope() {
//        session.close()
//    }


    override fun switchOffUiInteraction(flag: Boolean) {

        fun blockScreen() {
            rootActivity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        fun unBlockScreen() {
            rootActivity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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