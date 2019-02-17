package com.example.darkfox.trainingnotes.utils.enums

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.darkfox.trainingnotes.R

enum class MuscleGroups(@StringRes val value:Int) {
    Breast(R.string.breast_name),
    Legs(R.string.legs_name),
    Back(R.string.back_name),
    Shoulders(R.string.shoulders_name),
    Biceps(R.string.biceps_name),
    Triceps(R.string.triceps_name),
    ABS(R.string.abs_name)
}