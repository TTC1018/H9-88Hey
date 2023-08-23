package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarInfoResponse
import com.softeer.data.model.CarTempInfoBody
import com.softeer.data.model.MyArchiveMadeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyArchiveNetworkApi {

    @POST("/mychiving")
    suspend fun saveMyCarInfo(
        @Body carInfo: CarInfoBody
    ): Response<BaseResponse<CarInfoResponse>>

    @POST("/mychiving/temp")
    suspend fun saveMyCarTempInfo(
        @Body carInfo: CarTempInfoBody
    ): Response<BaseResponse<CarInfoResponse>>

    @GET("/mychiving")
    suspend fun getMadeCarFeeds(
        @Query("offset") offset: Int,
        @Query("limit") count: Int
    ): Response<BaseResponse<MyArchiveMadeDto>>

    @DELETE("/mychiving/{id}")
    suspend fun deleteMadeCarFeed(
        @Path(value = "id") id: Long
    ): Response<BaseResponse<String>>

}