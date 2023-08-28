package com.softeer.domain.model

data class TrimOption(
    val id: Int,
    val modelId: Int? = null,
    val optionName: String,
    val optionDesc: String,
    val imageUrl: String,
    val price: Int,
    val maximumOutput: String? = null,
    val maximumTorque: String? = null,
)

data class TrimSimpleOption(
    val id: Int,
    val optionName: String,
    val price: Int,
)