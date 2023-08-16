package com.softeer.data.network

import com.softeer.data.model.BaseResponse
import com.softeer.data.model.BodyTypeDto
import com.softeer.data.model.EngineDto
import com.softeer.data.model.ModelDto
import com.softeer.data.model.TrimImageDto
import com.softeer.data.model.WheelDto
import retrofit2.Response
import retrofit2.http.GET

interface SelectTrimNetworkApi {

    @GET("/car/model/1/image")
    suspend fun getModelImages(): Response<BaseResponse<TrimImageDto>>

    @GET("/car/model/1/trim")
    suspend fun getModels(): Response<BaseResponse<ModelDto>>

    @GET("/car/model/1/engine")
    suspend fun getEngines(): Response<BaseResponse<EngineDto>>

    @GET("/car/model/1/body-type")
    suspend fun getBodyTypes(): Response<BaseResponse<BodyTypeDto>>

    @GET("/car/model/1/wheel-drive")
    suspend fun getWheelDrives(): Response<BaseResponse<WheelDto>>

}