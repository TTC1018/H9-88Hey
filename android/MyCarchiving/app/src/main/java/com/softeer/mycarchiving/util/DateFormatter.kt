package com.softeer.mycarchiving.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDateString(): String {
    val inputFormat = when {
        this.contains("T") -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        else -> SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }
    val outputFormat = SimpleDateFormat("yy년 MM월 dd일", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date as Date)
}