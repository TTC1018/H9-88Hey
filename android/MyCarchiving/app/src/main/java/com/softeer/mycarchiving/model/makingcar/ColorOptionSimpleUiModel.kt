package com.softeer.mycarchiving.model.makingcar

data class ColorOptionSimpleUiModel(
    val id: Int,
    val category: String,
    val carImageUrl: String? = null,
    val price: Int? = null,
    val imageUrl: String,
    val colorName: String
)
