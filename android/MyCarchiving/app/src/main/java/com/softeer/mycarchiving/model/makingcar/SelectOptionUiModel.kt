package com.softeer.mycarchiving.model.makingcar

data class SelectOptionUiModel(
    val name: String,
    val price: Int,
    val imageUrl: String,
    val tags: List<String>? = null,
    val subOptions: List<SubSelectOptionUiModel>? = null
)

