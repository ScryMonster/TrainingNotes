package com.example.darkfox.trainingnotes.arch.ui.trainings

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.adapters.TrainingDayAdapter
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.TrainingsContract
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.extensions.buildWithAction
import com.example.darkfox.trainingnotes.utils.extensions.gone
import com.example.darkfox.trainingnotes.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_trainings.*
import org.koin.standalone.inject

class TrainingsFragment : BaseFragment<TrainingsContract.View, TrainingsContract.Presenter>(), TrainingsContract.View {
    override val layoutId: Int = R.layout.fragment_trainings
    override val presenter: TrainingsContract.Presenter by inject()
    override val scopeName: String = KoinScopes.TRAININGS.scopeName

    private var addItemMenu: MenuItem? = null
    private var progressItemMenu: MenuItem? = null
    private val adapter = TrainingDayAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hasOptionsMenu()
        toolbar.title = resources.getString(R.string.my_trainings)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        setHasOptionsMenu(true)
        initListeners()
        userTrainingsRV.buildWithAction(adapter)
        presenter.getTrainingDaysById()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.trainings_main_menu, menu)
        addItemMenu = menu.findItem(R.id.menu_item_add_training)
//        progressItemMenu  = menu.findItem(R.id.menu_item_progress)
    }

    override fun setTrainingDays(days: List<TrainingDay>) {
        if (days.isEmpty()) {
            emptyTrainingsView.visible()
            adapter.setList(days as ArrayList<TrainingDay>)
            addItemMenu?.isVisible = false
            progressItemMenu?.isVisible = false
        } else {
            emptyTrainingsView.gone()
            addItemMenu?.isVisible = true
            progressItemMenu?.isVisible = true
            adapter.setList(days as ArrayList<TrainingDay>, notify = true)
        }
    }

    override fun returnToEnterUserFlow() {
        rootActivity?.returnToEnterUserFlow()
    }

    override fun showProgress(tag: Any?) {
        rootActivity?.showProgress()
    }

    override fun hideProgress(tag: Any?) {
        rootActivity?.hideProgress()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_add_training -> {
                val direction = TrainingsFragmentDirections.openNewTraining(null)
                findNavController().navigate(direction)
            }

            R.id.menu_item_search -> {
                val direction = TrainingsFragmentDirections.openSearch()
                findNavController().navigate(direction)
            }
        }
        return true
    }

    private fun initListeners() {
        newTrainingIV.setOnClickListener {
            val direction = TrainingsFragmentDirections.openNewTraining(null)
            findNavController().navigate(direction)
        }

        adapter
                .setTrainingListener {
                    val direction = TrainingsFragmentDirections.openNewTraining(it)
                    findNavController().navigate(direction)
                }
                .setTrainingDeletionListener { day ->
                    adapter.deleteDay(day)
                    adapter.notifyDataSetChanged()
                    presenter.deleteTraining(day)
                }
                .setTrainingRemoveListener { day ->
                    presenter.removeTraining(day)
//                    adapter.notifyDataSetChanged()
                }
    }

}