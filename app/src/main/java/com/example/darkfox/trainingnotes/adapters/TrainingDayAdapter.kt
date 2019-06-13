package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.extensions.*
import kotlinx.android.synthetic.main.item_training_day.view.*

class TrainingDayAdapter : BaseAdapter<TrainingDayAdapter.TrainingDayViewHolder, TrainingDay>() {

    private val trainingAdapter = TrainingAdapter()
    private var trainingListener: (TrainingDayHolder) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingDayAdapter.TrainingDayViewHolder(parent.inflate(R.layout.item_training_day))

    override fun onBindViewHolder(holder: TrainingDayAdapter.TrainingDayViewHolder, position: Int) {
        holder.bind(itemList[position], position, trainingListener, trainingAdapter)
    }

    fun setTrainingListener(listener: (TrainingDayHolder) -> Unit) = apply {
        this.trainingListener = listener
    }


    class TrainingDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: TrainingDay, position: Int, listener: (TrainingDayHolder) -> Unit, adapter: TrainingAdapter) {
            itemView.apply {

                tvTrainingDayDate.text = item.getDate()
                adapter.setListener { training ->
                    listener(TrainingDayHolder(item.id,training))
                }
                rvTrainingDescription.buildWithAction(adapter)
                adapter.setList(item.trainings as ArrayList<Training>)

            }
        }

    }
}