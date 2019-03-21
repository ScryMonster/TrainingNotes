package com.example.darkfox.trainingnotes.dto.gym.states

import android.widget.LinearLayout
import com.example.darkfox.trainingnotes.dto.gym.TrainingState.*
import com.example.darkfox.trainingnotes.dto.gym.TrainingState

class TrainingStateMachine(
        private val emptyState: State,
        private val plannedState: State,
        private val finishedState: State,
        private val passedState: State) {

    private lateinit var layout: LinearLayout

    fun setLayout(layout: LinearLayout) = apply { this.layout = layout }

    fun start(state: TrainingState) {
        when (state) {
            EMPTY -> emptyState.execute(layout)
            PLANNED -> plannedState.execute(layout)
            FINISHED -> finishedState.execute(layout)
            PASSED ->  passedState.execute(layout)
        }
    }
}