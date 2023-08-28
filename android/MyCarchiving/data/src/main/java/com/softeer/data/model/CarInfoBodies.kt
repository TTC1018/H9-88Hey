package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class CarInfoBody(
    @SerializedName("contentType")
    val contentType: String = "application/json",
    @SerializedName("myChivingId")
    val myChivingId: String,
    @SerializedName("bodyType")
    val bodyType: Int,
    @SerializedName("wheelType")
    val wheelType: Int,
    @SerializedName("engine")
    val engine: Int,
    @SerializedName("trim")
    val trim: Int,
    @SerializedName("exteriorColor")
    val exteriorColor: Int,
    @SerializedName("interiorColor")
    val interiorColor: Int,
    @SerializedName("selectOptions")
    val selectOptions: List<String>
)

data class CarTempInfoBody(
    @SerializedName("contentType")
    val contentType: String = "application/json",
    @SerializedName("myChivingId")
    val myChivingId: String?,
    @SerializedName("bodyType")
    val bodyType: Int?,
    @SerializedName("wheelType")
    val wheelType: Int?,
    @SerializedName("engine")
    val engine: Int?,
    @SerializedName("trim")
    val trim: Int?,
    @SerializedName("exteriorColor")
    val exteriorColor: Int?,
    @SerializedName("interiorColor")
    val interiorColor: Int?,
    @SerializedName("selectOptions")
    val selectOptions: List<String>?
)