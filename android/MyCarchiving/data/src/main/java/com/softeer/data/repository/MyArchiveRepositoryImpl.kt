package com.softeer.data.repository

import com.softeer.data.datasource.MyArchiveDataSource
import com.softeer.data.mapper.asBody
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import com.softeer.domain.repository.MyArchiveRepository
import kotlinx.coroutines.flow.Flow

class MyArchiveRepositoryImpl(
    private val archiveRemoteDataSource: MyArchiveDataSource
) : MyArchiveRepository {

    override fun saveCarInfo(carInfo: CarInfo): Flow<Long> =
        archiveRemoteDataSource.saveCarInfo(carInfo.asBody())

    override fun saveTempCarInfo(carTempInfo: CarTempInfo): Flow<Long> =
        archiveRemoteDataSource.saveTempCarInfo(carTempInfo.asBody())

}