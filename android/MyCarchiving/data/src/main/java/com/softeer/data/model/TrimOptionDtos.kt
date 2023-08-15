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

data class HGenuineDto(
    @SerializedName("selectOptions")
    val selectOptions: List<TrimHGenuineDto>
)

data class TrimHGenuineDto(
    @SerializedName("isAvailable")
    val isAvailable: Boolean,
    @SerializedName("selectOption")
    val selectOption: TrimSelectOptionDto
)

data class TrimSelectOptionDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageUrl: String,
    @SerializedName("additionalPrice")
    val price: Int,
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