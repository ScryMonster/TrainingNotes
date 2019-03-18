package com.example.darkfox.trainingnotes.arch.base.ui

import android.view.View
import com.example.darkfox.trainingnotes.dto.custom.DoubleProgressView

abstract class BaseProgressFragment : BaseFragment(),IProgressView {

    abstract val progressView: DoubleProgressView
    abstract val background: View

    override fun showProgress(tag: Any?) {

    }

    override fun hideProgress(tag: Any?) {

    }
}