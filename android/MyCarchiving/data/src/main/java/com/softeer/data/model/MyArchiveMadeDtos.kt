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
    val id: String,
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