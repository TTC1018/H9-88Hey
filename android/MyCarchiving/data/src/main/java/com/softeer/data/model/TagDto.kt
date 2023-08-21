package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("tags")
    val tags: List<String>
)