package com.softeer.data.repository

import com.softeer.data.datasource.SignDataSource
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.SignInRequestDto
import com.softeer.domain.model.Token
import com.softeer.domain.repository.SignRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignRepositoryImpl(
    private val signDataSource: SignDataSource
) : SignRepository {
    override fun singIn(email: String, password: String): Flow<Token> =
        signDataSource.signIn(request = SignInRequestDto(email, password))
            .map { it.asEntity() }
}