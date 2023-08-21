package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class SignInRequestDto(
    @SerializedName("email")
    val email: String,
    val password: String,
)
