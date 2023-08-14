package com.softeer.data.repository

import com.softeer.data.datasource.SelectTrimDataSource
import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimModelDto
import com.softeer.data.model.TrimWheelDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SelectTrimRepository {
    fun getCarImageUrls(): Flow<List<String>>

    fun getModels(): Flow<List<TrimModelDto>>

    fun getEngines(): Flow<List<TrimEngineDto>>

    fun getBodyTypes(): Flow<List<TrimBodyTypeDto>>

    fun getWheelDrives(): Flow<List<TrimWheelDto>>
}

class SelectTrimRepositoryImpl (
    val selectTrimRemoteDataSource: SelectTrimDataSource
) : SelectTrimRepository {
    override fun getCarImageUrls(): Flow<List<String>> =
        selectTrimRemoteDataSource.getCarImages()

    override fun getModels(): Flow<List<TrimModelDto>> =
        selectTrimRemoteDataSource.getModels()

    override fun getEngines(): Flow<List<TrimEngineDto>> =
        selectTrimRemoteDataSource.getEngines()

    override fun getBodyTypes(): Flow<List<TrimBodyTypeDto>> =
        selectTrimRemoteDataSource.getBodyTypes()

    override fun getWheelDrives(): Flow<List<TrimWheelDto>> =
        selectTrimRemoteDataSource.getWheelDrives()
}