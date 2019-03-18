package com.example.darkfox.trainingnotes.utils.enums

enum class TimePatterns(val format:String) {
    JUST_DAY_OF_WEEK_PATTERN("EEE"),
    JUST_DAY_PATTERN("d"),
    DAY_OF_WEEK_WITH_NUMBER_PATTERN("EEE.d"),
    DAY_WEEK_MONTH_PATTERN("d EEE MMM")
}