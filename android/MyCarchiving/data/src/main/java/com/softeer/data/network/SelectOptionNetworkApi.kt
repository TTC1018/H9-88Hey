package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.BasicOptionDto
import com.softeer.data.model.CarCodeDto
import com.softeer.data.model.SelectOptionDto
import com.softeer.data.model.TagDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SelectOptionNetworkApi {
    @GET("/car/car-code")
    suspend fun getCarCode(
        @Query("trim_id") trimId: Int,
        @Query("engine_id") engineId: Int,
        @Query("body_type_id") bodyType: Int,
        @Query("wheel_drive_id") wheelDriveId: Int
    ): Response<BaseResponse<CarCodeDto>>

    @GET("/car/select-option")
    suspend fun getSelectOptions(
        @Query("car_code") carCode: String
    ): Response<BaseResponse<SelectOptionDto>>

    @GET("/car/h-genuine-accessories")
    suspend fun getHGenuines(
        @Query("car_code") carCode: String,
        @Query("select_option") selectOptions: List<String>
    ): Response<BaseResponse<SelectOptionDto>>

    @GET("/car/n-performance")
    suspend fun getNPerformances(
        @Query("car_code") carCode: String
    ): Response<BaseResponse<SelectOptionDto>>

    @GET("/car/default-option")
    suspend fun getBasicOptions(
        @Query("car_code") carCode: String
    ): Response<BaseResponse<BasicOptionDto>>

    @GET("/car/tag/select-option")
    suspend fun getTagsOfSelectOption(
        @Query("id") id: String,
        @Query("limit") count: Int = 4,
    ): Response<BaseResponse<TagDto>>

}