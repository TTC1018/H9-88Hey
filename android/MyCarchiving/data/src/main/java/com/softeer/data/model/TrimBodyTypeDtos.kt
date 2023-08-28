package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class BodyTypeDto(
    @SerializedName("bodyTypes")
    val bodyTypes: List<TrimBodyTypeDto>
)

data class TrimBodyTypeDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("additionalPrice")
    val price: Int,
    @SerializedName("description")
    val description: String
)