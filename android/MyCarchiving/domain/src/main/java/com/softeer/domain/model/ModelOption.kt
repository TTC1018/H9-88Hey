package com.softeer.domain.model

data class ModelOption(
    val id: Int,
    val name: String,
    val price: Int,
    val modelFeatures: List<ModelFeature>
)

data class ModelFeature(
    val name: String,
    val imageUrl: String
)