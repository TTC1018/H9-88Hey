package com.softeer.mycarchiving.model

data class TrimOptionUiModel(
    val id: Int,
    val optionName: String,
    val optionDesc: String,
    val imageUrl: String,
    val price: Int,
    val maximumOutput: String? = null,
    val maximumTorque: String? = null,
)
