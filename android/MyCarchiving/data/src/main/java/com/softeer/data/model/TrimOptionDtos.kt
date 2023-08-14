package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class CarCodeDto(
    @SerializedName("carCode")
    val carCode: String
)

data class SelectOptionDto(
    @SerializedName("selectOptions")
    val selectOptions: List<TrimSelectOptionDto>
)

data class TrimSelectOptionDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageUrl: String,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("subOptions")
    val subOptions: List<TrimSubOptionDto>
)

data class TrimSubOptionDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
)