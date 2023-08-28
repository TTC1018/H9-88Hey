package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.SignInRequestDto
import com.softeer.data.model.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignNetworkApi {

    @POST("/auth/signin")
    suspend fun signIn(
        @Body body: SignInRequestDto
    ) : Response<BaseResponse<TokenDto>>

    @POST("/auth/access-token/reissue")
    suspend fun reissue() : Response<BaseResponse<TokenDto>>
}