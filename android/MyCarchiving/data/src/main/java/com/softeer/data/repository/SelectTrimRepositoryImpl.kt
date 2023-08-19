package com.softeer.data.repository

import com.softeer.data.datasource.SelectTrimDataSource
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimModelDto
import com.softeer.data.model.TrimWheelDto
import com.softeer.domain.model.TrimModelOption
import com.softeer.domain.model.TrimOption
import com.softeer.domain.repository.SelectTrimRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SelectTrimRepositoryImpl(
    val selectTrimRemoteDataSource: SelectTrimDataSource
) : SelectTrimRepository {
    override fun getCarImageUrls(): Flow<List<String>> =
        selectTrimRemoteDataSource.getCarImages()

    override fun getModels(): Flow<List<TrimModelOption>> =
        selectTrimRemoteDataSource.getModels()
            .map { it.map(TrimModelDto::asEntity) }

    override fun getEngines(): Flow<List<TrimOption>> =
        selectTrimRemoteDataSource.getEngines()
            .map { it.map(TrimEngineDto::asEntity) }

    override fun getBodyTypes(): Flow<List<TrimOption>> =
        selectTrimRemoteDataSource.getBodyTypes()
            .map { it.map(TrimBodyTypeDto::asEntity) }

    override fun getWheelDrives(): Flow<List<TrimOption>> =
        selectTrimRemoteDataSource.getWheelDrives()
            .map { it.map(TrimWheelDto::asEntity) }
}