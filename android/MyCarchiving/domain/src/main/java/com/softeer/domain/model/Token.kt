package com.softeer.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val userName: String,
)
