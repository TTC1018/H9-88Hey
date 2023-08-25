package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class MyArchiveSavedDto(
    @SerializedName("nextOffset")
    val nextOffset: Int,
    @SerializedName("archivingsByUser")
    val savedCarFeeds: List<MyArchiveSavedFeedDto>
)

data class MyArchiveSavedFeedDto(
    @SerializedName("feedId")
    val id: Long,
    @SerializedName("purchase")
    val purchase: Boolean,
    @SerializedName("modelName")
    val modelName: String,
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("trim")
    val trim: MyArchiveTrimDto,
    @SerializedName("engine")
    val engine: MyArchiveTrimOptionDto,
    @SerializedName("bodyType")
    val bodyType: MyArchiveTrimOptionDto,
    @SerializedName("wheelDrive")
    val wheelDrive: MyArchiveTrimOptionDto,
    @SerializedName("exteriorColor")
    val exteriorColor: MyArchiveExteriorDto,
    @SerializedName("interiorColor")
    val interiorColor: MyArchiveInteriorDto,
    @SerializedName("review")
    val review: String? = null,
    @SerializedName("tags")
    val tags: List<String>? = null,
    @SerializedName("selectedOptions")
    val selectedOptions: List<MyArchiveSelectedDto>
)