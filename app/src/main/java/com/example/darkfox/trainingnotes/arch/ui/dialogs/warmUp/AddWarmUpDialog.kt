package com.example.darkfox.trainingnotes.arch.ui.dialogs.warmUp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseBottomSheetDialog
import com.example.darkfox.trainingnotes.arch.ui.contracts.AddWarmUpContract
import com.example.darkfox.trainingnotes.arch.ui.createTraining.CreateTrainingFragment
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.dto.gym.Exercise
import com.example.darkfox.trainingnotes.dto.gym.WarmUp
import com.example.darkfox.trainingnotes.utils.enums.AddExercisePart
import com.example.darkfox.trainingnotes.utils.enums.CreateTraningBackResult
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_add_exercise.*
import kotlinx.android.synthetic.main.dialog_add_war_up.*
import org.koin.android.ext.android.inject
import kotlin.random.Random

class AddWarmUpDialog : BaseBottomSheetDialog<AddWarmUpContract.View, AddWarmUpContract.Presenter>(), AddWarmUpContract.View {
    override val scopeName: String = KoinScopes.ADD_WARM_UP.scopeName
    override val presenter: AddWarmUpContract.Presenter by inject()


    private var warmUp: WarmUp? = null
    private var exercise: Exercise? = null
    private var exercisePart: AddExercisePart? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        arguments?.let { bundle ->
            val saveArgs = AddWarmUpDialogArgs.fromBundle(bundle)
            warmUp = saveArgs.warmUp
            exercisePart = saveArgs.exercisePart
            exercise = saveArgs.exercise
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState).apply {

            val view = when (exercisePart) {
                AddExercisePart.EXERCISES -> {
                    View.inflate(context, R.layout.dialog_add_exercise, null)
                }
                AddExercisePart.WARMUP -> {
                    View.inflate(context, R.layout.dialog_add_war_up, null)
                }
                else -> {
                    View.inflate(context, R.layout.dialog_add_war_up, null)
                }
            }
            setContentView(view)

            when (exercisePart) {
                AddExercisePart.WARMUP -> startWarmUpFlow(this)
                AddExercisePart.EXERCISES -> startExerciseFlow(this)
            }



            btnSaveWarmUp?.setOnClickListener {
                when {
                    etExerciseWarmUpNameField.text?.isEmpty()!! -> tvExerciseWarmUpName.error = resources.getString(R.string.add_war_up_error_name_required)
                    etExerciseTimeField.text?.isEmpty()!! -> etExerciseTime.error = resources.getString(R.string.add_war_up_error_time_required)
                    else -> goBackWithWarmUp(WarmUp(id = warmUp?.id ?:Random.nextInt(),name = etExerciseWarmUpNameField.text.toString(), time = "${etExerciseTimeField.text} m"))
                }
            }

            btnSaveExercise?.setOnClickListener {
                if (etExerciseNameField.text?.isEmpty()!!) tvExerciseName.error = resources.getString(R.string.add_exercise_error_name_required)
                else goBackWithExercise(Exercise(id = exercise?.id ?: Random.nextInt(),name = etExerciseNameField.text.toString(), rounds = listOf(
                        firstRound.getRoundDetails(), secondRound.getRoundDetails(), thirdRound.getRoundDetails(),
                        fourthRound.getRoundDetails(), fifthRound.getRoundDetails()
                )))

            }


        }

        return dialog
    }

    private fun startWarmUpFlow(dialog: Dialog) {
        warmUp?.let {
            dialog.etExerciseWarmUpNameField.setText(it.name)
            dialog.etExerciseTimeField.setText(it.time.replace("m",""))
        }
        dialog.etExerciseWarmUpNameField.requestFocus()
    }

    private fun startExerciseFlow(dialog:Dialog) {
        exercise?.let {
            dialog.etExerciseNameField.setText(it.name)
            dialog.firstRound.setRoundDetails(it.rounds[0])
            dialog.secondRound.setRoundDetails(it.rounds[1])
            dialog.thirdRound.setRoundDetails(it.rounds[2])
            dialog.fourthRound.setRoundDetails(it.rounds[3])
            dialog.fifthRound.setRoundDetails(it.rounds[4])
        }
        dialog.etExerciseNameField.requestFocus()
    }

    private fun goBackWithWarmUp(warmUp: WarmUp) {
        val args = Bundle().apply {
            putParcelable(WARM_UP, warmUp)
            putString(CreateTrainingFragment.BACK_RESULT, CreateTraningBackResult.WARM_UP.name)
        }
        dismiss()
        (activity as RootActivity).navigateBackWithResult(args)
    }

    private fun goBackWithExercise(exercise: Exercise) {
        val args = Bundle().apply {
            putParcelable(EXERCISE, exercise)
            putString(CreateTrainingFragment.BACK_RESULT, CreateTraningBackResult.EXERCISE.name)
        }
        dismiss()
        (activity as RootActivity).navigateBackWithResult(args)
    }


    companion object {
        const val WARM_UP = "AddWarmUpDialog#warmUp"
        const val EXERCISE = "AddWarmUpDialog#exercise"
    }

}