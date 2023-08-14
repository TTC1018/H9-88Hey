package com.softeer.data.datasource

import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimModelDto
import com.softeer.data.model.TrimWheelDto
import com.softeer.data.network.SelectTrimNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private val TAG = SelectTrimDataSource::class.simpleName

interface SelectTrimDataSource {
    fun getCarImages(): Flow<List<String>>

    fun getModels(): Flow<List<TrimModelDto>>

    fun getEngines(): Flow<List<TrimEngineDto>>

    fun getBodyTypes(): Flow<List<TrimBodyTypeDto>>

    fun getWheelDrives(): Flow<List<TrimWheelDto>>
}

class SelectTrimRemoteDataSource (
    val selectTrimNetworkApi: SelectTrimNetworkApi
) : SelectTrimDataSource {
    override fun getCarImages(): Flow<List<String>> = flow {
        val response = selectTrimNetworkApi.getModelImages()
        val carImageUrls = response.body()?.data?.carImageUrls

        if (response.isSuccessful && carImageUrls != null) {
            emit(carImageUrls)
        } else {
            emit(emptyList())
        }
    }

    override fun getModels(): Flow<List<TrimModelDto>> = flow {
        val response = selectTrimNetworkApi.getModels()
        val models = response.body()?.data?.models

        if (response.isSuccessful && models != null) {
            emit(models)
        } else {
            emit(emptyList())
        }
    }

    override fun getEngines(): Flow<List<TrimEngineDto>> = flow {
        val response = selectTrimNetworkApi.getEngines()
        val trims = response.body()?.data?.engines


        if (response.isSuccessful && trims != null) {
            emit(trims)
        } else {
            emit(emptyList())
        }
    }

    override fun getBodyTypes(): Flow<List<TrimBodyTypeDto>> = flow {
        val response = selectTrimNetworkApi.getBodyTypes()
        val bodyTypes = response.body()?.data?.bodyTypes

        if (response.isSuccessful && bodyTypes != null) {
            emit(bodyTypes)
        } else {
            emit(emptyList())
        }
    }

    override fun getWheelDrives(): Flow<List<TrimWheelDto>> = flow {
        val response = selectTrimNetworkApi.getWheelDrives()
        val wheelDrives = response.body()?.data?.wheels

        if (response.isSuccessful && wheelDrives != null) {
            emit(wheelDrives)
        } else {
            emit(emptyList())
        }
    }
}