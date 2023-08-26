package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarInfoResponse
import com.softeer.data.model.CarTempInfoBody
import com.softeer.data.model.MyArchiveBookMarkDto
import com.softeer.data.model.MyArchiveFeedIdDto
import com.softeer.data.model.MyArchiveMadeDto
import com.softeer.data.model.MyArchiveSavedDto
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
        @Path("id") id: String
    ): Response<BaseResponse<String>>

    @GET("/user/archiving/bookmark")
    suspend fun getSavedCarFeeds(
        @Query("offset") offset: Int,
        @Query("limit") count: Int
    ): Response<BaseResponse<MyArchiveSavedDto>>

    @GET("/user/archiving/{id}/bookmark")
    suspend fun checkBookMarked(
        @Path("id") id: String
    ): Response<BaseResponse<MyArchiveBookMarkDto>>

    @POST("/user/archiving/{id}/bookmark")
    suspend fun addBookMark(
        @Path("id") id: String
    ): Response<BaseResponse<MyArchiveFeedIdDto>>

    @DELETE("/user/archiving/{id}/bookmark")
    suspend fun deleteBookMark(
        @Path("id") id: String
    ): Response<BaseResponse<Unit>>

}