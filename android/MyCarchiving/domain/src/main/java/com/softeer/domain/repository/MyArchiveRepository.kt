package com.softeer.domain.repository

import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import kotlinx.coroutines.flow.Flow

interface MyArchiveRepository {

    fun saveCarInfo(carInfo: CarInfo): Flow<String>

    fun saveTempCarInfo(carTempInfo: CarTempInfo): Flow<String>

}