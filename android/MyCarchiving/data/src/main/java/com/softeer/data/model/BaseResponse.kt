package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T,
)
