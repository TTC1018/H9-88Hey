package com.softeer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.softeer.data.datasource.MyArchiveDataSource
import com.softeer.data.datasource.MyArchiveMadeFeedPagingSource
import com.softeer.data.datasource.MyArchiveSavedFeedPagingSource
import com.softeer.data.datasource.RemotePagingSource
import com.softeer.data.mapper.asBody
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.MyArchiveMadeFeedDto
import com.softeer.data.model.MyArchiveSavedFeedDto
import com.softeer.data.network.MyArchiveNetworkApi
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.repository.MyArchiveRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyArchiveRepositoryImpl(
    private val myArchiveNetworkApi: MyArchiveNetworkApi,
    private val archiveRemoteDataSource: MyArchiveDataSource
) : MyArchiveRepository {

    override fun saveCarInfo(carInfo: CarInfo): Flow<String> =
        archiveRemoteDataSource.saveCarInfo(carInfo.asBody())

    override fun saveTempCarInfo(carTempInfo: CarTempInfo): Flow<String> =
        archiveRemoteDataSource.saveTempCarInfo(carTempInfo.asBody())

    override fun getMadeCarFeeds(): Flow<PagingData<MyArchiveFeed>> =
        Pager(
            config = PagingConfig(pageSize = RemotePagingSource.PAGING_SIZE),
            pagingSourceFactory = {
                MyArchiveMadeFeedPagingSource(myArchiveNetworkApi)
            }
        ).flow.map { pagingData ->
            pagingData.map(MyArchiveMadeFeedDto::asEntity)
        }

    override suspend fun deleteMadeCarFeed(feedId: String): Boolean =
        archiveRemoteDataSource.deleteMadeCar(feedId)

    override fun getSavedCarFeeds(): Flow<PagingData<MyArchiveFeed>> =
        Pager(
            config = PagingConfig(pageSize = RemotePagingSource.PAGING_SIZE),
            pagingSourceFactory = {
                MyArchiveSavedFeedPagingSource(myArchiveNetworkApi)
            }
        ).flow.map { pagingData ->
            pagingData.map(MyArchiveSavedFeedDto::asEntity)
        }


    override suspend fun checkBookmarked(feedId: String): Boolean =
        archiveRemoteDataSource.checkBookmarked(feedId)

    override suspend fun addBookmark(feedId: String): String? =
        archiveRemoteDataSource.addBookmark(feedId)

    override suspend fun deleteBookmark(feedId: String): Boolean =
        archiveRemoteDataSource.deleteBookmark(feedId)
    }