package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.CarCodeDto
import com.softeer.data.model.SelectOptionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SelectOptionNetworkApi {
    @GET("/car/car-code")
    suspend fun getCarCode(
        @Query("trim_id") trimId: Int = 1,
        @Query("engine_id") engineId: Int = 1,
        @Query("body_type_id") bodyType: Int = 1,
        @Query("wheel_drive_id") wheelDriveId: Int = 1
    ): Response<BaseResponse<CarCodeDto>>

    @GET("/car/select-option")
    suspend fun getSelectOptions(
        @Query("carCode") carCode: String
    ): Response<BaseResponse<SelectOptionDto>>

    // TODO H-GENUINE 선언

    @GET("/car/n-performance")
    suspend fun getNPerformances(
        @Query("carCode") carCode: String
    ): Response<BaseResponse<SelectOptionDto>>
}