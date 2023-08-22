package com.softeer.data.datasource

import com.softeer.data.model.SignInRequestDto
import com.softeer.data.model.TokenDto
import com.softeer.data.network.SignNetworkApi

interface SignDataSource {
    suspend fun signIn(request: SignInRequestDto): TokenDto?

    suspend fun reissue(refreshToken: String): TokenDto?
}

class SignRemoteDataSource(
    private val signNetworkApi: SignNetworkApi
) : SignDataSource {
    override suspend fun signIn(request: SignInRequestDto): TokenDto? {
        val response = signNetworkApi.signIn(request)
        val token = response.body()?.data
        if (response.isSuccessful) {
            return token
        }
        return null
    }

    override suspend fun reissue(refreshToken: String): TokenDto? {
        val response = signNetworkApi.reissue("Bearer $refreshToken")
        val token = response.body()?.data
        if (response.isSuccessful) {
            return token
        }
        return null
    }
}