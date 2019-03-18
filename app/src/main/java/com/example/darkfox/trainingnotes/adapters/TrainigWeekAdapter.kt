package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.BaseViewHolder
import com.example.darkfox.trainingnotes.dto.Training
import com.example.darkfox.trainingnotes.utils.enums.TimePatterns
import com.example.darkfox.trainingnotes.utils.extensions.convertToTime
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_training_rv.view.*

class TrainigWeekAdapter : BaseAdapter<TrainigWeekAdapter.TrainingViewHolder,Training>() {
    init {
        itemList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder  = TrainingViewHolder(parent.inflate(R.layout.item_training_rv))

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(itemList[position])
    }


    class TrainingViewHolder(val view:View) : BaseViewHolder<Training>(view){
        override fun bind(item: Training, position: Int) {
            itemView.trainingDateTV.text = item.date convertToTime TimePatterns.JUST_DAY_PATTERN
            itemView.trainingDayTV.text = item.date convertToTime TimePatterns.JUST_DAY_OF_WEEK_PATTERN
        }

    }
}