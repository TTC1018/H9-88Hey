package com.softeer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.softeer.data.datasource.ArchivingDataSource
import com.softeer.data.datasource.ArchivingRemotePagingSource
import com.softeer.data.datasource.RemotePagingSource
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.ArchivingFeedDto
import com.softeer.data.model.ArchivingSelectOptionDto
import com.softeer.data.network.ArchivingNetworkApi
import com.softeer.domain.model.CarDetails
import com.softeer.domain.model.CarFeed
import com.softeer.domain.model.SelectSimpleOption
import com.softeer.domain.repository.ArchivingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArchivingRepositoryImpl(
    private val archivingNetworkApi: ArchivingNetworkApi,
    private val archivingRemoteDataSource: ArchivingDataSource
) : ArchivingRepository {
    override fun getSelectOptions(): Flow<List<SelectSimpleOption>> =
        archivingRemoteDataSource.getSelectOptions()
            .map { it.map(ArchivingSelectOptionDto::asEntity) }

    override fun getCarFeeds(selectOptions: List<String>): Flow<PagingData<CarFeed>> =
        Pager(
            config = PagingConfig(pageSize = RemotePagingSource.PAGING_SIZE),
            pagingSourceFactory = {
                ArchivingRemotePagingSource(
                    archivingNetworkApi,
                    selectOptions
                )
            }
        ).flow.map { pagingData ->
            pagingData.map(ArchivingFeedDto::asEntity)
        }

    override fun getCarDetails(feedId: Long): Flow<CarDetails?> =
        archivingRemoteDataSource.getCarDetails(feedId)
            .map { it?.run { this.asEntity() } }
}