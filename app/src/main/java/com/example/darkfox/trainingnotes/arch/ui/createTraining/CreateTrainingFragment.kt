package com.example.darkfox.trainingnotes.arch.ui.createTraining

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.adapters.WarmUpAdapter
import com.example.darkfox.trainingnotes.arch.base.NavigationResultView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseFragment
import com.example.darkfox.trainingnotes.arch.ui.contracts.CreateTrainingContract
import com.example.darkfox.trainingnotes.arch.ui.dialogs.AddWarmUpDialog
import com.example.darkfox.trainingnotes.dto.gym.WarmUp
import com.example.darkfox.trainingnotes.utils.enums.CreateTraningBackResult
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.extensions.buildWithAction
import com.example.darkfox.trainingnotes.utils.extensions.gone
import com.example.darkfox.trainingnotes.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_create_training.*
import org.koin.standalone.inject

class CreateTrainingFragment : BaseFragment<CreateTrainingContract.View, CreateTrainingContract.Presenter>(), CreateTrainingContract.View,NavigationResultView {
    override val layoutId: Int = R.layout.fragment_create_training
    override val presenter: CreateTrainingContract.Presenter by inject()
    override val scopeName: String = KoinScopes.CREATE_TRAINING.scopeName

    private val warmUpAdapter = WarmUpAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        rvWarmUp.buildWithAction(warmUpAdapter)
    }

    override fun onNavigationResult(bundle: Bundle) {
        val backResult = bundle.getString(BACK_RESULT)
        when(backResult){
            CreateTraningBackResult.WARM_UP.name->{
                val warmUp = bundle.getParcelable<WarmUp>(AddWarmUpDialog.WARM_UP)
                warmUpAdapter.addItem(warmUp)
            }
            CreateTraningBackResult.EXERCISE.name->{}
        }
    }

    private fun initListeners(){
        warmUpAdapter.setListener {
            val direction = CreateTrainingFragmentDirections.openWarmUpDialog(it)
            findNavController().navigate(direction)
        }

        btnAddWarmUp.setOnClickListener {
            btnAddWarmUp.gone()
            rvWarmUp.visible()
            val direction = CreateTrainingFragmentDirections.openWarmUpDialog(null)
            findNavController().navigate(direction)
        }
    }


    companion object {
        const val BACK_RESULT = "CreateTrainingFragment#backResult"
    }

}