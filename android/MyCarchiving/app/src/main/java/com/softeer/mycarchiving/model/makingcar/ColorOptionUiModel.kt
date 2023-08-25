package com.softeer.mycarchiving.model.makingcar

import com.softeer.domain.model.CarColorType

data class ColorOptionUiModel(
    val id: Int,
    val category: CarColorType,
    val optionName: String,
    val imageUrl: String,
    val price: Int,
    val matchingColors: List<Int>,
    val tags: List<String>,
) {
    fun asSimpleUiModel() = ColorOptionSimpleUiModel(
        id = id,
        category = category.type,
        imageUrl = imageUrl,
        price = price,
        colorName = optionName
    )
}