package com.softeer.data.network

import com.softeer.data.model.ArchivingDetailsDto
import com.softeer.data.model.ArchivingDto
import com.softeer.data.model.ArchivingFeedDto
import com.softeer.data.model.BaseResponse
import com.softeer.data.model.SelectOptionsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArchivingNetworkApi {

    @GET("/archiving")
    suspend fun getCarFeeds(
        @Query("model_id") modelId: Int = 1,
        @Query("offset") offset: Int,
        @Query("limit") count: Int,
        @Query("select_option") selectOptions: List<String>,
    ): Response<BaseResponse<ArchivingDto>>

    @GET("/car/select-options")
    suspend fun getArchiveOptions(
        @Query("model_id") modelId: Int = 1
    ): Response<BaseResponse<SelectOptionsDto>>

    @GET("/archiving/{feed_id}")
    suspend fun getCarDetails(
        @Path("feed_id") feedId: String,
    ): Response<BaseResponse<ArchivingDetailsDto>>

}