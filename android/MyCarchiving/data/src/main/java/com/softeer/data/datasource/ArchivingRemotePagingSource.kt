package com.softeer.data.datasource

import com.softeer.data.model.ArchivingFeedDto
import com.softeer.data.network.ArchivingNetworkApi

class ArchivingRemotePagingSource(
    private val archivingNetworkApi: ArchivingNetworkApi,
    private val selectOptions: List<String>,
) : RemotePagingSource<ArchivingFeedDto>() {
    override suspend fun loadResult(
        params: LoadParams<Int>,
        pageNumber: Int,
        prevKey: Int?
    ): LoadResult<Int, ArchivingFeedDto> {
        val response = archivingNetworkApi.getCarFeeds(
            offset = pageNumber,
            count = PAGING_SIZE,
            selectOptions = selectOptions
        )
        val feeds = response.body()?.data?.carFeeds
        val nextKey = if (feeds?.isEmpty() == true) null else pageNumber + 1
        return if (feeds != null) LoadResult.Page(feeds, prevKey, nextKey) else LoadResult.Invalid()
    }
}