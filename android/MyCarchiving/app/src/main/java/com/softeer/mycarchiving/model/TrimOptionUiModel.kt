package com.softeer.mycarchiving.model

data class TrimOptionUiModel(
    val optionName: String,
    val optionDesc: String? = null,
    val imageUrl: String? = null,
    val price: Int? = null,
    val maximumOutput: String? = null,
    val maximumTorque: String? = null,
)
