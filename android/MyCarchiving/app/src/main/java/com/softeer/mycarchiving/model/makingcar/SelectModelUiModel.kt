package com.softeer.mycarchiving.model.makingcar

import kotlinx.serialization.Serializable

@Serializable
data class SelectModelUiModel(
    val id: Int,
    val name: String,
    val price: Int,
    val features: List<ModelFeatureUiModel>
): java.io.Serializable

@Serializable
data class ModelFeatureUiModel(
    val name: String,
    val imageUrl: String
): java.io.Serializable
