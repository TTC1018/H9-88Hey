package com.softeer.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.softeer.data.model.ArchivingFeedDto
import com.softeer.data.network.ArchivingNetworkApi

private val TAG = ArchivingRemotePagingSource::class.simpleName

class ArchivingRemotePagingSource(
    private val archivingNetworkApi: ArchivingNetworkApi,
    private val selectOptions: List<String>,
) : PagingSource<Int, ArchivingFeedDto>() {

    override fun getRefreshKey(state: PagingState<Int, ArchivingFeedDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArchivingFeedDto> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = archivingNetworkApi.getCarFeeds(
                offset = pageNumber,
                count = PAGING_SIZE,
                selectOptions = selectOptions
            )
            val carFeeds = response.body()?.data?.carFeeds
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (carFeeds?.isEmpty() == true) null else pageNumber + 1

            Log.d(TAG, carFeeds?.joinToString("\n") ?: "")
            if (carFeeds != null) {
                LoadResult.Page(carFeeds, prevKey, nextKey)
            } else {
                LoadResult.Invalid()
            }
        } catch (e: Exception) {
            Log.d(TAG, e.stackTraceToString())
            LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val PAGING_SIZE = 10
    }
}