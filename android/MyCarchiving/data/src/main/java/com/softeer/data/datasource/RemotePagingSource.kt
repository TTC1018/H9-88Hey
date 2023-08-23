package com.softeer.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class RemotePagingSource<V : Any> : PagingSource<Int, V>() {

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition?.let {  anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            loadResult(params, pageNumber, prevKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    abstract suspend fun loadResult(
        params: LoadParams<Int>,
        pageNumber: Int,
        prevKey: Int?
    ): LoadResult<Int, V>

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val PAGING_SIZE = 10
    }
}