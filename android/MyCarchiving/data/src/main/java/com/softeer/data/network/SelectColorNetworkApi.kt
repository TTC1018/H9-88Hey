package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.CarColorDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SelectColorNetworkApi {

    @GET("/car/color")
    suspend fun getCarColors(@Query("trim_id") trimId: Int = 1): Response<BaseResponse<CarColorDto>>

}