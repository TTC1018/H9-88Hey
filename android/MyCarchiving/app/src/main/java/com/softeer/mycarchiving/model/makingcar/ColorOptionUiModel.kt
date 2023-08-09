package com.softeer.mycarchiving.model.makingcar

data class ColorOptionUiModel(
    val optionName: String,
    val imageUrl: String,
    val price: Int,
    val matchingColors: List<Int>,
    val tags: List<String>,
)