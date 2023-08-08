package com.softeer.mycarchiving.model.common

data class CarBasicDetailUiModel(
    val id: Int,
    val detailName: String,
    val description: String,
    val detailImageUrl: String? = null
)