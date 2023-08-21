package com.softeer.data.datasource

import com.softeer.data.model.SignInRequestDto
import com.softeer.data.model.TokenDto
import com.softeer.data.network.SignNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SignDataSource {
    fun signIn(request: SignInRequestDto): Flow<TokenDto>
}

class SingRemoteDataSource(
    private val signNetworkApi: SignNetworkApi
) : SignDataSource {
    override fun signIn(request: SignInRequestDto): Flow<TokenDto> = flow {
        val response = signNetworkApi.signIn(request)
        val token = response.body()?.data
        if (response.isSuccessful) {
            token?.let { emit(it) }
        }
    }
}