package com.example.darkfox.trainingnotes.arch.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.adapters.SearchAdapter
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.SearchContract
import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.enums.SearchType
import com.example.darkfox.trainingnotes.utils.extensions.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment<SearchContract.View, SearchContract.Presenter>(), SearchContract.View {
    override val layoutId: Int = R.layout.fragment_search
    override val presenter: SearchContract.Presenter by inject()
    override val scopeName: String = KoinScopes.SEARCH.scopeName

    private var textSearchMenuItem:MenuItem? = null
    private var groupSearchMenuItem:MenuItem? = null
    private var searchType = SearchType.TEXT

    private val searchAdapter = SearchAdapter()
            .setListener { training ->
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
        etSearch.showKeyboard()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.clearLastSearchedList()
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                etSearch.hideKeyboard()
                etSearch.clearFocus()
                findNavController().navigateUp()
            }
            R.id.menu_item_muscle-> switchViewsDependsOnSearchType(SearchType.GROUPS)
            R.id.menu_item_name->switchViewsDependsOnSearchType(SearchType.TEXT)

        }

        return true
    }

    private fun switchViewsDependsOnSearchType(type:SearchType){
        when(type){
            SearchType.TEXT->{
                textSearchMenuItem?.isVisible = false
                etSearch.setText(emptyString)
                etSearch.enable()
                btnClearSearch.visible()
                btnSetupSearch.gone()
            }
            SearchType.GROUPS ->{
                groupSearchMenuItem?.isVisible = false
                etSearch.setText(emptyString)
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
        etSearch.afterTextChanged { name ->
            if (etSearch.isEnabled) presenter.searchByName(name)
        }

        btnClearSearch.setOnClickListener {
            etSearch.setText(emptyString)
        }

        btnSetupSearch.setOnClickListener {

        }
    }


}