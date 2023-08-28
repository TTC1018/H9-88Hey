package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class WheelDto(
    @SerializedName("wheelDrives")
    val wheels: List<TrimWheelDto>
)

data class TrimWheelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("additionalPrice")
    val price: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("modelId")
    val modelId: Int
)
