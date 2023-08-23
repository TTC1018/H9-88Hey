package com.softeer.data.datasource

import com.softeer.data.model.MyArchiveMadeFeedDto
import com.softeer.data.network.MyArchiveNetworkApi

class MyArchiveMadeFeedPagingSource(
    private val myArchiveNetworkApi: MyArchiveNetworkApi,
) : RemotePagingSource<MyArchiveMadeFeedDto>() {

    override suspend fun loadResult(
        params: LoadParams<Int>,
        pageNumber: Int,
        prevKey: Int?
    ): LoadResult<Int, MyArchiveMadeFeedDto> {
        val response = myArchiveNetworkApi.getMadeCarFeeds(
            offset = pageNumber,
            count = PAGING_SIZE
        )
        val feeds = response.body()?.data?.madeCarFeeds
        val nextKey = if (feeds?.isEmpty() == true) null else pageNumber + 1
        return if (feeds != null) LoadResult.Page(feeds, prevKey, nextKey) else LoadResult.Invalid()
    }
}