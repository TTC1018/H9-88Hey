package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class MyArchiveModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class MyArchiveTrimDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)

data class MyArchiveTrimOptionDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("additionalPrice")
    val price: Int
)

data class MyArchiveExteriorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String,
    @SerializedName("carImageUrl")
    val carImageUrl: String,
    @SerializedName("additionalPrice")
    val price: Int
)

data class MyArchiveInteriorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String
)

data class MyArchiveSelectedDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("additionalPrice")
    val price: Int,
    @SerializedName("subOptions")
    val subOptions: List<String>? = null,
    @SerializedName("tags")
    val tags: List<String>? = null
)

data class MyArchiveBookMarkDto(
    @SerializedName("bookmark")
    val bookmark: Boolean
)

data class MyArchiveFeedIdDto(
    @SerializedName("feedId")
    val feedId: String
)
