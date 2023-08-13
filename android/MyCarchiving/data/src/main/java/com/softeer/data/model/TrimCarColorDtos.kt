package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class CarColorDto(
    @SerializedName("exteriorColors")
    val exteriors: List<TrimExteriorCarColor>,
    @SerializedName("interiorColors")
    val interiors: List<TrimInteriorCarColor>,
)

sealed class TrimCarColorDto

data class TrimExteriorCarColor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("carImagePath")
    val carImagePath: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String,
    @SerializedName("additionalPrice")
    val additionalPrice: Int,
    @SerializedName("availableInteriorColors")
    val subInteriors: List<Int>,
    @SerializedName("tags")
    val tags: List<String>?
): TrimCarColorDto()

data class TrimInteriorCarColor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("carImageUrl")
    val carImageUrl: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String,
    @SerializedName("tags")
    val tags: List<String>?
): TrimCarColorDto()