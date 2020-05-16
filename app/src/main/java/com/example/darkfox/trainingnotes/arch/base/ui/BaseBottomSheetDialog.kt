package com.example.darkfox.trainingnotes.arch.base.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.darkfox.trainingnotes.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetDialog<V : BaseContract.View, P : BaseContract.Presenter<V>> : BottomSheetDialogFragment(), BaseContract.View {

    abstract val presenter: P


    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        presenter.attachView(this as V)
        return dialog
    }


    protected fun initBottomSheetBehavior(dialog: Dialog, contentView: View) {
        val layoutParams = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        (behavior as BottomSheetBehavior<*>).state = BottomSheetBehavior.STATE_EXPANDED
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
//                            popupHandle?.setImageResource(R.drawable.ic_popup_handle_bottom_position)
                            dialog.dismiss()
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
//                            popupHandle?.setImageResource(R.drawable.ic_popup_handle_top_position)
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
//                            popupHandle?.setImageResource(R.drawable.ic_popup_handle_top_position)
                        }
                        BottomSheetBehavior.STATE_DRAGGING -> {

                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    //mPopupHandle?.setImageResource(R.drawable.ic_popup_handle_top_position)
                }
            })
        }
        dialog.window?.decorView?.findViewById<View>(R.id.design_bottom_sheet)?.setBackgroundResource(android.R.color.transparent)
        val d = dialog as BottomSheetDialog
        val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
        bottomSheetBehavior.skipCollapsed = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }


    override fun errorMessage(message: Int) {}

    override fun errorMessage(message: String) {}

    override fun infoMessage(message: Int) {}

    override fun infoMessage(message: String) {}

    override fun switchOffUiInteraction(flag: Boolean) {}

}