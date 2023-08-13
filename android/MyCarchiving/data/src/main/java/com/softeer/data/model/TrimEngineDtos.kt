package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class EngineDto(
    @SerializedName("engines")
    val engines: List<TrimEngineDto>
)

data class TrimEngineDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("additionalPrice")
    val price: Int,
    @SerializedName("modelId")
    val modelId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("maximumPower")
    val maximumPower: String,
    @SerializedName("maximumTorque")
    val maximumTorque: String,
)