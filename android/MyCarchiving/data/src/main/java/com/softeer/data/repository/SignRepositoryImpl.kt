package com.softeer.data.repository

import com.softeer.data.datasource.SignDataSource
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.SignInRequestDto
import com.softeer.domain.model.Token
import com.softeer.domain.repository.SignRepository

class SignRepositoryImpl(
    private val signDataSource: SignDataSource
) : SignRepository {
    override suspend fun singIn(email: String, password: String): Token? =
        signDataSource.signIn(request = SignInRequestDto(email, password))?.asEntity()

    override suspend fun reissue(): Token? =
        signDataSource.reissue()?.asEntity()

}