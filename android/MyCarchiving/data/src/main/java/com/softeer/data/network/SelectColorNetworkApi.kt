package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.CarColorDto
import com.softeer.data.model.TagDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SelectColorNetworkApi {

    @GET("/car/color")
    suspend fun getCarColors(@Query("trim_id") trimId: Int): Response<BaseResponse<CarColorDto>>

    @GET("/car/tag/interior-color")
    suspend fun getTagsOfInterior(
        @Query("id") id: Int,
        @Query("limit") count: Int = 4,
    ): Response<BaseResponse<TagDto>>

    @GET("/car/tag/exterior-color")
    suspend fun getTagsOfExterior(
        @Query("id") id: Int,
        @Query("limit") count: Int = 4,
    ): Response<BaseResponse<TagDto>>

}