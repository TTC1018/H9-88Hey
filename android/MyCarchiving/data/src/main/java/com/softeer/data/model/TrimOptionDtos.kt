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

data class BasicOptionDto(
    @SerializedName("defaultOptions")
    val defaultOptions: List<TrimBasicOptionDto>
)

data class TrimBasicOptionDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("subOptions")
    val subOptions: List<TrimBasicSubOptionDto>
)

data class TrimBasicSubOptionDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("description")
    val description: String
)

data class TrimSelectOptionDto(
    @SerializedName("isAvailable")
    val isAvailable: Boolean?,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
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
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
)