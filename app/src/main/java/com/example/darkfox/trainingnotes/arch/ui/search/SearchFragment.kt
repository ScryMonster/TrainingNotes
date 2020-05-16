package com.example.darkfox.trainingnotes.arch.ui.search

import android.os.Bundle
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.adapters.SearchAdapter
import com.example.darkfox.trainingnotes.arch.base.NavigationResultView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchContract
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import com.example.darkfox.trainingnotes.utils.enums.SearchBackResult
import com.example.darkfox.trainingnotes.utils.enums.SearchType
import com.example.darkfox.trainingnotes.utils.extensions.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import java.util.*

class SearchFragment : BaseFragment<SearchContract.View, SearchContract.Presenter>(), SearchContract.View,NavigationResultView {
    override val layoutId: Int = R.layout.fragment_search
    override val presenter: SearchContract.Presenter by inject()

    private var textSearchMenuItem:MenuItem? = null
    private var groupSearchMenuItem:MenuItem? = null
    private var searchType = SearchType.TEXT

    private var selectedGroups:List<MuscleGroups>? = null
    private var selectedDate: Date? = null
    private var isFirstTime = true
    private var currentTextWatcher:TextWatcher? = null

    private val searchAdapter = SearchAdapter()
            .setListener { training ->
                isFirstTime = false
                val direction = SearchFragmentDirections.openTraining(training)
                findNavController().navigate(direction)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initListeners()
        setHasOptionsMenu(true)
        etSearch.requestFocus()
        switchViewsDependsOnSearchType(searchType)
        if (searchType == SearchType.TEXT) etSearch.showKeyboard()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (!isFirstTime){
            presenter.clearLastSearchedList()
            when(searchType){
                SearchType.GROUPS->{
                    presenter.searchByGroup(selectedGroups)
//                    initTextChangeListener {}
                }
                SearchType.DATE->{}
                else ->{}
            }
        }
    }

    override fun setTrainings(trainings: List<TrainingDayHolder>) {
        if (trainings.isNotEmpty()) {
            rvSearchSuggestions.visible()
            emptyResultsContainer.gone()
            searchAdapter.setList(trainings as ArrayList<TrainingDayHolder>, notify = true, clear = true)
        }
        else {
            rvSearchSuggestions.gone()
            emptyResultsContainer.visible()
        }
    }

    override fun setClearBtnVisibility(visible: Boolean) {
        if (visible) btnClearSearch.visible()
        else btnClearSearch.gone()
    }

    override fun hideProgress(tag: Any?) {
        rootActivity?.hideProgress()
    }

    override fun showProgress(tag: Any?) {
        rootActivity?.showProgress()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu,menu)
        textSearchMenuItem = menu.findItem(R.id.menu_item_name)
        groupSearchMenuItem = menu.findItem(R.id.menu_item_muscle)
        when(searchType){
            SearchType.TEXT->{
                textSearchMenuItem?.isVisible = false
            }
            SearchType.GROUPS->{
                groupSearchMenuItem?.isVisible = false
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                etSearch.hideKeyboard()
                etSearch.clearFocus()
                findNavController().navigateUp()
            }
            R.id.menu_item_muscle->{
                searchType = SearchType.GROUPS
                switchViewsDependsOnSearchType(searchType)
                openSettingsByGroup()
            }
            R.id.menu_item_name->{
                searchType = SearchType.TEXT
                switchViewsDependsOnSearchType(searchType)
            }

        }

        return true
    }

    override fun onNavigationResult(bundle: Bundle) {
        when(bundle.getSerializable(BACK_RESULT) as SearchBackResult){
            SearchBackResult.TEXT->{}
            SearchBackResult.GROUPS->{
                selectedGroups =  bundle.getStringArrayList(BACK_RESULT_GROUPS)?.map { it.toMuscleGroup() }
                val stringRepresentation = selectedGroups?.toStringRepresentation()
                etSearch.setText(stringRepresentation)
                presenter.searchByGroup(selectedGroups)
            }
            SearchBackResult.DATE->{

            }
        }
    }

    override fun warningMeaage(type: SearchType) {
        when(type){
            SearchType.GROUPS->{
                resources.getString(R.string.search_warning_no_groups_entered).showInfoInSnackBar(requireView())
                etSearch.setText(R.string.search_by_training_group)
            }
            SearchType.DATE->{
                resources.getString(R.string.search_warning_no_date_entered).showInfoInSnackBar(requireView())
                etSearch.setText(R.string.search_by_training_date)
            }
            else ->{}
        }
    }

    private fun switchViewsDependsOnSearchType(type:SearchType){
        when(type){
            SearchType.TEXT->{
                textSearchMenuItem?.isVisible = false
                groupSearchMenuItem?.isVisible = true
                etSearch.enable()
                initTextChangeListener { name ->
                    presenter.searchByName(name)
                }
                etSearch.setText(emptyString)
                etSearch.requestFocus()
//                btnClearSearch.visible()
                btnSetupSearch.gone()
            }
            SearchType.GROUPS ->{
                groupSearchMenuItem?.isVisible = false
                textSearchMenuItem?.isVisible = true
                initTextChangeListener {}
                etSearch.setText(R.string.search_by_training_group)
                etSearch.disable()
                btnClearSearch.gone()
                btnSetupSearch.visible()
            }
        }
    }


    private fun initListeners(){
        rvSearchSuggestions.buildWithBaseLayoutManagerAndAnimator {
            adapter = searchAdapter
        }

        initTextChangeListener { name ->
            if (etSearch.isEnabled) presenter.searchByName(name)
        }

        btnClearSearch.setOnClickListener {
            etSearch.setText(emptyString)
        }

        btnSetupSearch.setOnClickListener {
            openSettingsByGroup()
        }
    }

    private fun openSettingsByGroup(){
//        isFirstTime = false
        val direction = SearchFragmentDirections.openSearchSettings(SearchType.GROUPS, selectedGroups?.map { it.name }?.toTypedArray(),null)
        findNavController().navigate(direction)
    }

    private fun initTextChangeListener(listener:(String)->Unit){
        if (etSearch.tag != null){
            etSearch.removeTextChangedListener(currentTextWatcher)
        }
        currentTextWatcher = etSearch.afterTextChangedWithWatcher(listener)
        etSearch.tag = ""
    }


    companion object {
        const val BACK_RESULT_TEXT = "backResultText"
        const val BACK_RESULT_GROUPS = "backResultGroups"
        const val BACK_RESULT_DATE = "backResultDate"
        const val BACK_RESULT = "backResultSearchSettings"
    }


}