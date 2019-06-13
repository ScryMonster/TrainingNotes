package com.example.darkfox.trainingnotes.dto.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.dto.gym.Round
import kotlinx.android.synthetic.main.exercise_round_view.view.*

class ExerciseRound @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttributeSet: Int = 0,
                                              defStyleRes: Int = 0): LinearLayout(context, attrs,defStyleAttributeSet,defStyleRes) {

    private var view:View? = null

    init {
        createView(attrs,defStyleRes)
    }

    private fun createView(attrs: AttributeSet?, defStyleRes: Int?){
        view = View.inflate(context, R.layout.exercise_round_view, this)
        val array = context.obtainStyledAttributes(attrs,R.styleable.ExerciseRound,defStyleRes!!,0)
        val round:Int = array.getInteger(R.styleable.ExerciseRound_roundNumber,1)

        roundNumber.text = round.toString()

    }

    fun setRoundDetails(round:Round){
        exerciseRepeats.setText(round.repeats.toString())
        exerciseWeightField.setText(round.weight.toString())
    }

    fun getRoundDetails() = Round(
            if (exerciseWeightField.text?.isEmpty()!!) 0.0 else exerciseWeightField.text.toString().toDouble(),
            if (exerciseRepeats.text?.isEmpty()!!)  0 else exerciseRepeats.text.toString().toInt()
    )
}