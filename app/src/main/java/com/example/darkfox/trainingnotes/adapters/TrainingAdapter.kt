package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.dto.gym.TrainingState
import com.example.darkfox.trainingnotes.utils.extensions.getDate
import com.example.darkfox.trainingnotes.utils.extensions.getTime
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import com.example.darkfox.trainingnotes.utils.extensions.toStringRepresentation
import kotlinx.android.synthetic.main.item_training_description.view.*

class TrainingAdapter : BaseAdapter<TrainingAdapter.TrainingViewHolder,Training>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingViewHolder(parent.inflate(R.layout.item_training_description))

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(itemList[position], position, clickListener)
    }

    fun deleteItem(position: Int){
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun deleteItem(item: Training){
        itemList = itemList.minus(item) as ArrayList<Training>

        notifyDataSetChanged()
    }


    class TrainingViewHolder(view:View) : BaseViewHolder<Training>(view){
        override fun bind(item: Training, position: Int, listener: (Training) -> Unit) {
            itemView.setOnClickListener {
                listener(item)
            }

            itemView.apply {
                setOnClickListener {
                    listener(item)
                }
                trainingsTimeValueTV.text = item.getTime()
                trainingMusclesValueTV.text = item.muscules.toStringRepresentation()
                trainingExercisesValueTV.text = item.exercises.size.toString()
                trainingDateValueTV.text = item?.getDate()
                trainingStatusColoredView.setBackgroundColor(
                        when (item.state) {
                            TrainingState.EMPTY -> R.color.light_grey
                            TrainingState.PLANNED -> R.color.trueBlue
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

                trainingStatusTV.setTextColor(
                        when (item.state) {
                            TrainingState.EMPTY -> ContextCompat.getColor(context,android.R.color.black)
                            TrainingState.PLANNED -> ContextCompat.getColor(context,android.R.color.white)
                            TrainingState.FINISHED -> ContextCompat.getColor(context,android.R.color.black)
                            TrainingState.PASSED -> ContextCompat.getColor(context,R.color.mtrl_tabs_icon_color_selector_colored)
                            TrainingState.ACTIVE -> ContextCompat.getColor(context,R.color.sea_green)
                        }
                )
            }
        }

    }
}