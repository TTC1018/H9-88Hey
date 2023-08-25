package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class MyArchiveMadeDto(
    @SerializedName("nextOffset")
    val nextOffset: Int,
    @SerializedName("myChivings")
    val madeCarFeeds: List<MyArchiveMadeFeedDto>
)

data class MyArchiveMadeFeedDto(
    @SerializedName("myChivingId")
    val id: Long,
    @SerializedName("lastModifiedDate")
    val lastModifiedDate: String,
    @SerializedName("isSaved")
    val isSaved: Boolean,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("model")
    val model: MyArchiveModelDto,
    @SerializedName("trim")
    val trim: MyArchiveTrimDto? = null,
    @SerializedName("engine")
    val engine: MyArchiveTrimOptionDto? = null,
    @SerializedName("bodyType")
    val bodyType: MyArchiveTrimOptionDto? = null,
    @SerializedName("wheelDrive")
    val wheelDrive: MyArchiveTrimOptionDto? = null,
    @SerializedName("interiorColor")
    val interiorColor: MyArchiveInteriorDto? = null,
    @SerializedName("exteriorColor")
    val exteriorColor: MyArchiveExteriorDto? = null,
    @SerializedName("selectOptions")
    val selectedOptions: List<MyArchiveSelectedDto>
)

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

data class MyArchiveInteriorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String
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
    val subOptions: List<String>? = null
)