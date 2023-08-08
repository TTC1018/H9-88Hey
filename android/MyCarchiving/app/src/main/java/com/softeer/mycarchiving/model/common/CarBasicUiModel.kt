package com.softeer.mycarchiving.model.common

data class CarBasicUiModel(
    val id: Int,
    val name: String,
    val detailItems: List<CarBasicDetailUiModel>
)