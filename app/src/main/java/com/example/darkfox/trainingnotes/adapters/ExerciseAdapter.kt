package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder
import com.example.darkfox.trainingnotes.models.dto.gym.Exercise
import com.example.darkfox.trainingnotes.utils.extensions.findAndReplace
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseAdapter : BaseAdapter<ExerciseAdapter.ExerciseViewHolder, Exercise>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExerciseViewHolder(parent.inflate(R.layout.item_exercise))

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(itemList[position], position, clickListener)
    }

    override fun addItem(item: Exercise) {
        if (itemList.any { it.id == item.id }) {
            itemList.findAndReplace(predicate = {it.id == item.id},
                    map = { it?.name = item.name;it?.rounds = item.rounds})
            notifyDataSetChanged()
        }
        else {
            itemList.add(item)
            notifyDataSetChanged()
        }

    }


    class ExerciseViewHolder(view: View) : BaseViewHolder<Exercise>(view) {
        override fun bind(item: Exercise, position: Int, listener: (Exercise) -> Unit) {
            itemView.apply {
                setOnClickListener {
                    listener(item)
                }
                tvExerciseWarmUpName.text = item.name
                roundOne.setDetails(item.rounds[0])
                roundTwo.setDetails(item.rounds[1])
                roundThree.setDetails(item.rounds[2])
                roundFour.setDetails(item.rounds[3])
                roundFive.setDetails(item.rounds[4])
            }

        }

    }
}