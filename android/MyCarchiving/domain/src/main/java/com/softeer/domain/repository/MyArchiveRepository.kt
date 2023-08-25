package com.softeer.domain.repository

import androidx.paging.PagingData
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import kotlinx.coroutines.flow.Flow

interface MyArchiveRepository {

    fun saveCarInfo(carInfo: CarInfo): Flow<Long>

    fun saveTempCarInfo(carTempInfo: CarTempInfo): Flow<Long>

    fun getMadeCarFeed(): Flow<PagingData<MyArchiveFeed>>

    suspend fun deleteMadeCarFeed(feedId: Long): Boolean

}