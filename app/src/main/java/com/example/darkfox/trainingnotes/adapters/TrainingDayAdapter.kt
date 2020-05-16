package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.SwipeCallback
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.models.dto.gym.Training
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.extensions.*
import kotlinx.android.synthetic.main.item_training_day.view.*

class TrainingDayAdapter : BaseAdapter<TrainingDayAdapter.TrainingDayViewHolder, TrainingDay>() {

    private val trainingAdapter = TrainingAdapter()
    private var trainingListener: (TrainingDayHolder) -> Unit = {}
    private var trainingDeletionListener: (TrainingDay) -> Unit = {}
    private var trainingRemoveListener: (TrainingDay) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingDayAdapter.TrainingDayViewHolder(parent.inflate(R.layout.item_training_day))

    override fun onBindViewHolder(holder: TrainingDayAdapter.TrainingDayViewHolder, position: Int) {
        holder.bind(itemList[position], position, trainingListener, trainingAdapter, trainingDeletionListener,trainingRemoveListener)
    }

    fun setTrainingListener(listener: (TrainingDayHolder) -> Unit) = apply {
        this.trainingListener = listener
    }

    fun setTrainingDeletionListener(listener: (TrainingDay) -> Unit) = apply {
        this.trainingDeletionListener = listener
    }

    fun setTrainingRemoveListener(listener: (TrainingDay) -> Unit) = apply {
        this.trainingRemoveListener = listener
    }

    fun deleteDay(day: TrainingDay){
        itemList = itemList.minus(day) as ArrayList<TrainingDay>
        notifyDataSetChanged()
    }


    class TrainingDayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: TrainingDay, position: Int, listener: (TrainingDayHolder) -> Unit,
                 adapter: TrainingAdapter, deletionListener: (TrainingDay) -> Unit,
                 removeListener:(TrainingDay)->Unit) {
            itemView.apply {

                tvTrainingDayDate.text = item.getDate()
                adapter.setListener { training ->
                    listener(TrainingDayHolder(item.id, training))
                }
                rvTrainingDescription.buildWithAction(adapter) {
                    val swipeCallback = SwipeCallback().apply {
                        setIcon(ContextCompat.getDrawable(context, R.drawable.ic_delete)!!)
                        setSwipeListener { position ->
                            val trainingForDeletion = adapter.getList()[position]
                            adapter.deleteItem(trainingForDeletion)
                            if(item.trainings.any { it.id == trainingForDeletion.id }){
                                item.trainings = item.trainings.minus(trainingForDeletion)
                            }
                            if (item.trainings.isEmpty()){
                                deletionListener(item)
                            }
                            else {
                                removeListener(item)
                            }
                        }
                    }
                    ItemTouchHelper(swipeCallback).attachToRecyclerView(this)
                }
                adapter.setList(item.trainings as ArrayList<Training>)

            }
        }

    }
}