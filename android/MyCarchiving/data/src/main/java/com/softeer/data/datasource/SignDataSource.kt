package com.softeer.data.datasource

import com.softeer.data.model.SignInRequestDto
import com.softeer.data.model.TokenDto
import com.softeer.data.network.SignNetworkApi

interface SignDataSource {
    suspend fun signIn(request: SignInRequestDto): TokenDto?

    suspend fun reissue(): TokenDto?
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

    override suspend fun reissue(): TokenDto? {
        val response = signNetworkApi.reissue()
        val token = response.body()?.data
        if (response.isSuccessful) {
            return token
        }
        return null
    }
}