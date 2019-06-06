package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.dto.gym.TrainingState
import com.example.darkfox.trainingnotes.utils.extensions.getDate
import com.example.darkfox.trainingnotes.utils.extensions.getTime
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import com.example.darkfox.trainingnotes.utils.extensions.toStringRepresentation
import kotlinx.android.synthetic.main.item_training_description.view.*

class TrainingDayAdapter : BaseAdapter<TrainingDayAdapter.TrainingDayViewHolder, TrainingDay>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingDayAdapter.TrainingDayViewHolder(parent.inflate(R.layout.item_training_description))

    override fun onBindViewHolder(holder: TrainingDayAdapter.TrainingDayViewHolder, position: Int) {
        holder.bind(itemList[position], position, clickListener)
    }


    class TrainingDayViewHolder(view: View) : BaseViewHolder<TrainingDay>(view) {
        override fun bind(item: TrainingDay, position: Int, listener: (TrainingDay) -> Unit) {
            itemView.setOnClickListener {
                listener(item)
            }

            itemView.apply {
                trainingsTimeValueTV.text = item.training?.getTime()
                trainingMusclesValueTV.text = item.training?.muscules?.toStringRepresentation()
                trainingExercisesValueTV.text = item.training?.exercises?.size.toString()
                trainingDateValueTV.text = item.training?.getDate()
                trainingStatusColoredView.setBackgroundColor(
                        when (item.state) {
                            TrainingState.EMPTY -> R.color.light_grey
                            TrainingState.PLANNED -> R.color.lightBlue
                            TrainingState.FINISHED -> R.color.light_grey
                            TrainingState.ACTIVE -> R.color.spring_green
                            TrainingState.PASSED -> R.color.indian_red
                        }
                )
                trainingStatusTV.setText(
                        when (item.state) {
                            TrainingState.EMPTY -> R.string.state_empty
                            TrainingState.PLANNED -> R.string.state_planned
                            TrainingState.FINISHED -> R.string.state_finished
                            TrainingState.PASSED -> R.string.state_passed
                            TrainingState.ACTIVE -> R.string.state_active
                        }
                )
            }
        }

    }
}