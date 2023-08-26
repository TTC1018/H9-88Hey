package com.softeer.domain.repository

import androidx.paging.PagingData
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import kotlinx.coroutines.flow.Flow

interface MyArchiveRepository {

    fun saveCarInfo(carInfo: CarInfo): Flow<String>

    fun saveTempCarInfo(carTempInfo: CarTempInfo): Flow<String>

    fun getMadeCarFeeds(): Flow<PagingData<MyArchiveFeed>>

    suspend fun deleteMadeCarFeed(feedId: String): Boolean

    fun getSavedCarFeeds(): Flow<PagingData<MyArchiveFeed>>

    suspend fun checkBookmarked(feedId: String): Boolean

    suspend fun addBookmark(feedId: String): String?

    suspend fun deleteBookmark(feedId: String): Boolean

}