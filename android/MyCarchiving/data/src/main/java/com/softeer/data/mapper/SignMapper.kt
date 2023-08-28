package com.softeer.data.mapper

import com.softeer.data.model.TokenDto
import com.softeer.domain.model.Token

fun TokenDto.asEntity(): Token =
    Token(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userName = userName
    )