package com.softeer.data.datasource

import com.softeer.data.model.MyArchiveSavedFeedDto
import com.softeer.data.network.MyArchiveNetworkApi

class MyArchiveSavedFeedPagingSource(
    private val myArchiveNetworkApi: MyArchiveNetworkApi,
) : RemotePagingSource<MyArchiveSavedFeedDto>() {

    override suspend fun loadResult(
        params: LoadParams<Int>,
        pageNumber: Int,
        prevKey: Int?
    ): LoadResult<Int, MyArchiveSavedFeedDto> {
        val response = myArchiveNetworkApi.getSavedCarFeeds(
            offset = pageNumber,
            count = PAGING_SIZE
        )
        val feeds = response.body()?.data?.savedCarFeeds
        val nextKey = if (feeds?.isEmpty() == true) null else pageNumber + 1
        return if (feeds != null) LoadResult.Page(feeds, prevKey, nextKey) else LoadResult.Invalid()
    }
}