package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class ModelDto(
    @SerializedName("trims")
    val models: List<TrimModelDto>
)

data class TrimModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("trimFeatures")
    val modelFeatures: List<ModelFeatureDto>
)

data class ModelFeatureDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)