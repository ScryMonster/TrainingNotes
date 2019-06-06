package com.example.darkfox.trainingnotes.arch.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseBottomSheetDialog
import com.example.darkfox.trainingnotes.arch.ui.contracts.AddWarmUpContract
import com.example.darkfox.trainingnotes.arch.ui.createTraining.CreateTrainingFragment
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.dto.gym.WarmUp
import com.example.darkfox.trainingnotes.utils.enums.CreateTraningBackResult
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_add_war_up.*
import org.koin.android.ext.android.inject
import org.koin.core.scope.Scope

class AddWarmUpDialog : BaseBottomSheetDialog<AddWarmUpContract.View,AddWarmUpContract.Presenter>(),AddWarmUpContract.View {
    override val scopeName: String = KoinScopes.ADD_WARM_UP.scopeName
    override val presenter: AddWarmUpContract.Presenter by inject()
    override var popupHandle: ImageView? = null


    private var warmUp:WarmUp? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        arguments?.let { bundle ->
            val saveArgs = AddWarmUpDialogArgs.fromBundle(bundle)
            warmUp = saveArgs.warmUp
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState).apply {
            val view = View.inflate(context, R.layout.dialog_add_war_up,null)
            setContentView(view)

            popupHandle = this.mPopupHandle
            warmUp?.let {
                etExerciseNameField.setText(it.name)
                etExerciseTimeField.setText(it.time)
            }
            mPopupHandle.setOnClickListener {
                dismiss()
            }

            btnSave.setOnClickListener {
                val warmUp = WarmUp(etExerciseNameField.text.toString(),"${etExerciseTimeField.text} m")
                val args = Bundle().apply {
                    putParcelable(WARM_UP,warmUp)
                    putString(CreateTrainingFragment.BACK_RESULT,CreateTraningBackResult.WARM_UP.name)
                }
                dismiss()
                (activity as RootActivity).navigateBackWithResult(args)
            }



        }

        return dialog
    }

    companion object {
        const val WARM_UP = "AddWarmUpDialog#warmUp"
    }

}