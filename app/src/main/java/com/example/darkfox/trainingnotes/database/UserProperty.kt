package com.example.darkfox.trainingnotes.database

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.darkfox.trainingnotes.R
import kotlinx.android.synthetic.main.user_property.view.*

class UserProperty @JvmOverloads constructor(context: Context,
                                             attrs: AttributeSet? = null,
                                             defStyleAttributeSet: Int = 0,
                                             defStyleRes: Int = 0) : ConstraintLayout(context, attrs,defStyleAttributeSet,defStyleRes) {

    init {
        init(attrs, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyleRes: Int?){
        View.inflate(context, R.layout.user_property,this)
        val array = context.obtainStyledAttributes(attrs,R.styleable.UserProperty,defStyleRes!!,0)
        val title = array.getString(R.styleable.UserProperty_title)
        userPropertyTv.text = title
    }

    fun setValue(value:String){
        userPropertyValue.text = value
    }
}