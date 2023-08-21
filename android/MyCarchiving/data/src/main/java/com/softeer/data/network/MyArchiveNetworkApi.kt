package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarInfoResponse
import com.softeer.data.model.CarTempInfoBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MyArchiveNetworkApi {

    @POST("/mychiving")
    suspend fun saveMyCarInfo(
        @Body carInfo: CarInfoBody
    ): Response<BaseResponse<CarInfoResponse>>

    @POST("/mychiving/temp")
    suspend fun saveMyCarTempInfo(
        @Body carInfo: CarTempInfoBody
    ): Response<BaseResponse<CarInfoResponse>>


}