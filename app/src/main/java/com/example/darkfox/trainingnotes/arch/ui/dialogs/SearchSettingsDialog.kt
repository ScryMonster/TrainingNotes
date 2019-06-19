package com.example.darkfox.trainingnotes.arch.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.ui.BaseBottomSheetDialog
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchSettingsContract
import com.example.darkfox.trainingnotes.arch.ui.root.RootActivity
import com.example.darkfox.trainingnotes.arch.ui.search.SearchFragment
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import com.example.darkfox.trainingnotes.utils.enums.SearchBackResult
import com.example.darkfox.trainingnotes.utils.enums.SearchType
import com.example.darkfox.trainingnotes.utils.extensions.check
import com.example.darkfox.trainingnotes.utils.extensions.toMuscleGroup
import kotlinx.android.synthetic.main.dialog_muscle_group_settings.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class SearchSettingsDialog : BaseBottomSheetDialog<SearchSettingsContract.View, SearchSettingsContract.Presenter>(), SearchSettingsContract.View {
    override val presenter: SearchSettingsContract.Presenter by inject()
    override val scopeName: String = KoinScopes.SEARCH_SETTINGS.scopeName
    private var checkBoxes: List<CheckBox>? = null

    private var settingsType: SearchType = SearchType.GROUPS
    private var selectedGroups: List<MuscleGroups>? = null
    private var selectedDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val saveArgs = SearchSettingsDialogArgs.fromBundle(bundle)
            settingsType = saveArgs.settingsType
            selectedGroups = (saveArgs.selectedGroups?.toList())?.map { it.toMuscleGroup() }
            selectedDate = saveArgs.selectedDate
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState).apply {
            val view = View.inflate(requireContext(), R.layout.dialog_muscle_group_settings, null)
            setContentView(view)
            checkBoxes = arrayListOf(absCHB, backCHB, bicepsCHB, tricepsCHB, chestCHB, legsCHB, shouldersCHB)
            if (selectedGroups != null || selectedDate != null) fillViewsWithPreviousSettings(this)
            btnSaveSettings.setOnClickListener {
                val groups = arrayListOf<String>()
                checkBoxes?.filter { it.isChecked }?.forEach {
                    when (it) {
                        absCHB -> groups.add(MuscleGroups.ABS.name)
                        backCHB -> groups.add(MuscleGroups.Back.name)
                        bicepsCHB -> groups.add(MuscleGroups.Biceps.name)
                        tricepsCHB -> groups.add(MuscleGroups.Triceps.name)
                        chestCHB -> groups.add(MuscleGroups.Breast.name)
                        legsCHB -> groups.add(MuscleGroups.Legs.name)
                        shouldersCHB -> groups.add(MuscleGroups.Shoulders.name)
                    }
                }
                goBackToSearchWithGroups(groups)

            }
        }

        return dialog
    }

    private fun goBackToSearchWithGroups(groups:ArrayList<String>){
        val results = Bundle().apply {
            putStringArrayList(SearchFragment.BACK_RESULT_GROUPS, groups)
            putSerializable(SearchFragment.BACK_RESULT,SearchBackResult.GROUPS)
        }
        dismiss()
        (activity as RootActivity).navigateBackWithResult(results)
    }

    private fun goBackToSearchWithDate(date:Date){
        val results = Bundle().apply {
            putSerializable(SearchFragment.BACK_RESULT_DATE, date)
            putSerializable(SearchFragment.BACK_RESULT,SearchBackResult.GROUPS)
        }
        dismiss()
        (activity as RootActivity).navigateBackWithResult(results)
    }

    private fun fillViewsWithPreviousSettings(dialog: Dialog) {
        when (settingsType) {
            SearchType.GROUPS -> {
                selectedGroups?.let { groups ->
                    groups.forEach { group ->
                        when (group) {
                            MuscleGroups.ABS -> dialog.absCHB.check()
                            MuscleGroups.Back -> dialog.backCHB.check()
                            MuscleGroups.Biceps -> dialog.bicepsCHB.check()
                            MuscleGroups.Triceps -> dialog.tricepsCHB.check()
                            MuscleGroups.Breast -> dialog.chestCHB.check()
                            MuscleGroups.Legs -> dialog.legsCHB.check()
                            MuscleGroups.Shoulders -> dialog.shouldersCHB.check()
                        }
                    }
                }
            }
            SearchType.DATE -> {

            }
            else -> {
            }
        }
    }

}