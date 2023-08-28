package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class TrimImageDto(
    @SerializedName("carImageUrls")
    val carImageUrls: List<String>
)