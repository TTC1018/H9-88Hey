package com.softeer.domain.repository

import com.softeer.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface SignRepository {
    fun singIn(email: String, password: String): Flow<Token>
}