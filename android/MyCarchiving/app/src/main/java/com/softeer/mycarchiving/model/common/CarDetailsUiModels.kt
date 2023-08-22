package com.softeer.mycarchiving.model.common

import com.softeer.mycarchiving.model.makingcar.ColorOptionSimpleUiModel

data class CarDetailsUiModel(
    val id: Long,
    val model: String,
    val price: Int,
    val trim: String,
    val trimOptions: List<String>,
    val interiorColor: ColorOptionSimpleUiModel,
    val exteriorColor: ColorOptionSimpleUiModel,
    val selectedOptions: List<CarDetailSelectOptionUiModel>? = null,
    val review: String? = null,
)

data class CarDetailSelectOptionUiModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val reviewText: String,
    val subOptions: List<String>? = null,
    val tags: List<String>? = null
)