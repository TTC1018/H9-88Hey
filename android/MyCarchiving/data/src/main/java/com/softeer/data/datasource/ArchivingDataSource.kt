package com.softeer.data.datasource

import com.softeer.data.model.ArchivingDetailsDto
import com.softeer.data.model.ArchivingSelectOptionDto
import com.softeer.data.network.ArchivingNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ArchivingDataSource {
    fun getSelectOptions(): Flow<List<ArchivingSelectOptionDto>>

    fun getCarDetails(feedId: String): Flow<ArchivingDetailsDto?>
}

class ArchivingRemoteDataSource(
    private val archivingNetworkApi: ArchivingNetworkApi
): ArchivingDataSource {
    override fun getSelectOptions(): Flow<List<ArchivingSelectOptionDto>> = flow {
        val response = archivingNetworkApi.getArchiveOptions()
        val selectOptions = response.body()?.data?.selectOptions

        if (response.isSuccessful && selectOptions != null) {
            emit(selectOptions)
        } else {
            emit(emptyList())
        }
    }

    override fun getCarDetails(feedId: String): Flow<ArchivingDetailsDto?> = flow {
        val response = archivingNetworkApi.getCarDetails(feedId)
        val feed = response.body()?.data

        if (response.isSuccessful) {
            emit(feed)
        }
    }
}