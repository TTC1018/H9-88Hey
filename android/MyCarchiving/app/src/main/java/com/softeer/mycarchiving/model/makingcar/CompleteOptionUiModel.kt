package com.softeer.mycarchiving.model.makingcar

data class CompleteOptionUiModel(
    val optionName: String,
    val price: Int,
    val subOptionNames: List<String>,
    val thumbnailUrl: String? = null
)
