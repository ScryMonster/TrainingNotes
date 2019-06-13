package com.example.darkfox.trainingnotes.arch.ui.createTraining

import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.adapters.ExerciseAdapter
import com.example.darkfox.trainingnotes.adapters.WarmUpAdapter
import com.example.darkfox.trainingnotes.arch.base.NavigationResultView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.CreateTrainingContract
import com.example.darkfox.trainingnotes.arch.ui.dialogs.warmUp.AddWarmUpDialog
import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.dto.gym.Exercise
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.dto.gym.TrainingState
import com.example.darkfox.trainingnotes.dto.gym.WarmUp
import com.example.darkfox.trainingnotes.utils.enums.AddExercisePart
import com.example.darkfox.trainingnotes.utils.enums.CreateTraningBackResult
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import com.example.darkfox.trainingnotes.utils.extensions.*
import kotlinx.android.synthetic.main.fragment_create_training.*
import org.koin.standalone.inject
import kotlin.random.Random

class CreateTrainingFragment : BaseFragment<CreateTrainingContract.View, CreateTrainingContract.Presenter>(), CreateTrainingContract.View, NavigationResultView {
    override val layoutId: Int = R.layout.fragment_create_training
    override val presenter: CreateTrainingContract.Presenter by inject()
    override val scopeName: String = KoinScopes.CREATE_TRAINING.scopeName

    private val warmUpAdapter = WarmUpAdapter()
    private val exerciseAdapter = ExerciseAdapter()
    private var checkBoxes: ArrayList<CheckBox>? = null


    //    private var training: Training? = null
    private var dayHolder: TrainingDayHolder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val saveArgs = CreateTrainingFragmentArgs.fromBundle(bundle)
//            training = saveArgs.training
            dayHolder = saveArgs.dayHolder
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        rvWarmUp.buildWithAction(warmUpAdapter)
        rvExercises.buildWithAction(exerciseAdapter)
        tvTrainingState.text = resources.getString(R.string.save)
        tvTrainingState.setTextColor(WHITE)
        toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.lightBlue))
        checkBoxes = arrayListOf(absCHB, backCHB, bicepsCHB, tricepsCHB, chestCHB, legsCHB, shouldersCHB)
        trainingNameField.requestFocus()
        trainingNameField.showKeyboard()
        fillViewsWithPreviousTraining(dayHolder?.training)
    }

    override fun onNavigationResult(bundle: Bundle) {
        val backResult = bundle.getString(BACK_RESULT)
        when (backResult) {
            CreateTraningBackResult.WARM_UP.name -> {
                val warmUp = bundle.getParcelable<WarmUp>(AddWarmUpDialog.WARM_UP)
                warmUp?.let {
                    warUpContainer.visible()
                    warmUpAdapter.checkListAndAddItem(it)
                }
            }
            CreateTraningBackResult.EXERCISE.name -> {
                val exercise = bundle.getParcelable<Exercise>(AddWarmUpDialog.EXERCISE)
                exercise?.let {
                    //                    roundsBlock.visible()
                    rvExercises.visible()
                    exerciseAdapter.checkListAndAddItem(it)
                }
            }
        }
    }

    override fun returnToEnterUserFlow() {
        rootActivity?.returnToEnterUserFlow()
    }

    override fun goBack() {
        hideKeyboard()
        findNavController().navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                findNavController().navigateUp()
            }
        }

        return true
    }

    private fun hideKeyboard() {
        when {
            weightField.hasFocus() -> weightField
            trainingNameField.hasFocus() -> trainingNameField
            else -> null
        }?.apply {
            hideKeyboard()
            clearFocus()
        }
    }

    private fun initListeners() {
        warmUpAdapter.setListener {
            val direction = CreateTrainingFragmentDirections.openWarmUpDialog(it, AddExercisePart.WARMUP, null)
            findNavController().navigate(direction)
        }

        exerciseAdapter.setListener {
            val direction = CreateTrainingFragmentDirections.openWarmUpDialog(null, AddExercisePart.EXERCISES, it)
            findNavController().navigate(direction)
        }

        btnAddWarmUp.setOnClickListener {
            val direction = CreateTrainingFragmentDirections.openWarmUpDialog(null, AddExercisePart.WARMUP, null)
            findNavController().navigate(direction)
        }

        btnAddExercise.setOnClickListener {
            val direction = CreateTrainingFragmentDirections.openWarmUpDialog(null, AddExercisePart.EXERCISES, null)
            findNavController().navigate(direction)
        }

        toolbar.setOnClickListener {
            when {
                checkBoxes?.none { it.isChecked }!! -> {
                    errorMessage(R.string.none_muscle_group_error)
                    return@setOnClickListener
                }
                trainingNameField.text?.isEmpty()!! -> {
                    errorMessage(R.string.none_training_name_error)
                    return@setOnClickListener
                }

                weightField.text?.isEmpty()!! -> {
                    errorMessage(R.string.none_training_own_weight_error)
                    return@setOnClickListener
                }

                exerciseAdapter.itemCount == 0 -> {
                    errorMessage(R.string.none_training_exercises_error)
                    return@setOnClickListener
                }

                else -> {
                    Training(id = dayHolder?.training?.id ?: Random.nextLong(),
                            ownWeight = weightField.text?.toString()?.toDouble() ?: 0.0,
                            name = trainingNameField.text?.toString() ?: "",
                            muscules = getMuscleGoups(),
                            warmUP = warmUpAdapter.getList(),
                            exercises = exerciseAdapter.getList(),
                            from = dayHolder?.training?.from ?: System.currentTimeMillis(),
                            to = dayHolder?.training?.to ?: System.currentTimeMillis() + 100000,
                            state = dayHolder?.training?.state ?: TrainingState.PLANNED)
                            .also { training ->
                                this.dayHolder?.training?.let { initialTraining ->
                                    if (initialTraining == training) goBack() else presenter.saveTraining(dayHolder?.copy(training = training)!!)
                                } ?: presenter.saveTraining(TrainingDayHolder(null, training))
                            }
                }
            }
        }
    }

    override fun hideProgress(tag: Any?) {
        rootActivity?.hideProgress()
    }

    override fun showProgress(tag: Any?) {
        rootActivity?.showProgress()
    }


    companion object {
        const val BACK_RESULT = "CreateTrainingFragment#backResult"
    }

    private fun fillViewsWithPreviousTraining(training: Training?) {
        training?.let {
            warUpContainer.visible()
            warmUpAdapter.setList(it.warmUP as ArrayList<WarmUp>)
            rvExercises.visible()
            exerciseAdapter.setList(it.exercises as ArrayList<Exercise>)
            it.muscules.forEach { group ->
                when (group) {
                    MuscleGroups.ABS -> absCHB.check()
                    MuscleGroups.Back -> backCHB.check()
                    MuscleGroups.Biceps -> bicepsCHB.check()
                    MuscleGroups.Triceps -> tricepsCHB.check()
                    MuscleGroups.Breast -> chestCHB.check()
                    MuscleGroups.Legs -> legsCHB.check()
                    MuscleGroups.Shoulders -> shouldersCHB.check()
                }
            }

            trainingNameField.setText(it.name)
            weightField.setText(it.ownWeight.toString())
            tvTrainingState.text = resources.getString(R.string.update)
            toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.spring_green))

            //TODO Need to be uncommented when training states feature will be done/in process
//            when (it.state) {
//                TrainingState.EMPTY -> {
//                    tvTrainingState.text = resources.getString(R.string.state_empty)
//                    toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_grey))
//                }
//                TrainingState.PLANNED -> {
//                    tvTrainingState.text = resources.getString(R.string.state_planned)
//                    toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.lightBlue))
//                }
//                TrainingState.FINISHED -> {
//                    tvTrainingState.text = resources.getString(R.string.state_finished)
//                    toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_grey))
//                }
//                TrainingState.PASSED -> {
//                    tvTrainingState.text = resources.getString(R.string.state_passed)
//                    toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.indian_red))
//                }
//                TrainingState.ACTIVE -> {
//                    tvTrainingState.text = resources.getString(R.string.state_active)
//                    toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.spring_green))
//                }
//            }
        }
    }

    private fun getMuscleGoups(): List<MuscleGroups> {
        val groups = arrayListOf<MuscleGroups>()
        val filter = checkBoxes?.filter { it.isChecked }
        filter?.forEach {
            when (it) {
                absCHB -> groups.add(MuscleGroups.ABS)
                backCHB -> groups.add(MuscleGroups.Back)
                bicepsCHB -> groups.add(MuscleGroups.Biceps)
                tricepsCHB -> groups.add(MuscleGroups.Triceps)
                chestCHB -> groups.add(MuscleGroups.Breast)
                legsCHB -> groups.add(MuscleGroups.Legs)
                shouldersCHB -> groups.add(MuscleGroups.Shoulders)
            }
        }
        return groups
    }

}