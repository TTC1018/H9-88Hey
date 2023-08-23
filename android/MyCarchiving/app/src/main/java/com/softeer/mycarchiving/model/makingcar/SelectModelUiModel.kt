package com.softeer.mycarchiving.model.makingcar

data class SelectModelUiModel(
    val id: Int,
    val name: String,
    val price: Int,
    val features: List<ModelFeatureUiModel>
)

data class ModelFeatureUiModel(
    val name: String,
    val imageUrl: String
)
