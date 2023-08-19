package com.softeer.domain.model

data class TrimModelOption(
    val id: Int,
    val name: String,
    val price: Int,
    val modelFeatures: List<TrimModelFeature>
)

data class TrimModelFeature(
    val name: String,
    val imageUrl: String
)