package com.softeer.domain.repository

import com.softeer.domain.model.Token

interface SignRepository {
    suspend fun singIn(email: String, password: String): Token?

    suspend fun reissue(refreshToken: String): Token?
}