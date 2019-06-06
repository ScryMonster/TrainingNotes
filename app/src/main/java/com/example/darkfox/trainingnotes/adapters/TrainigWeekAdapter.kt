package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.dto.gym.states.TrainingStateMachine
import com.example.darkfox.trainingnotes.utils.enums.TimePatterns
import com.example.darkfox.trainingnotes.utils.extensions.convertToTime
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_training_rv.view.*

class TrainigWeekAdapter(private val machine: TrainingStateMachine) : BaseAdapter<TrainigWeekAdapter.TrainingViewHolder, TrainingDay>() {
    init {
        itemList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder = TrainingViewHolder(parent.inflate(R.layout.item_training_rv))

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(itemList[position], position,machine)
    }


    class TrainingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: TrainingDay, position: Int,machine: TrainingStateMachine) {
            itemView.trainingDateTV.text = item.id convertToTime TimePatterns.JUST_DAY_PATTERN
            itemView.trainingDayTV.text = item.id convertToTime TimePatterns.JUST_DAY_OF_WEEK_PATTERN

        }

    }
}