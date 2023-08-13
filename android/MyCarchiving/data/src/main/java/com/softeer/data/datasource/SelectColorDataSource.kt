package com.softeer.data.datasource

import com.softeer.data.model.TrimCarColorDto
import com.softeer.data.network.SelectColorNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SelectColorDataSource {
    fun getCarColors(): Flow<List<TrimCarColorDto>>
}

class SelectColorRemoteDataSource(
    private val selectColorNetworkApi: SelectColorNetworkApi
): SelectColorDataSource {
    override fun getCarColors(): Flow<List<TrimCarColorDto>> = flow {
        val response = selectColorNetworkApi.getCarColors()
        val exteriors = response.body()?.data?.exteriors
        val interiors = response.body()?.data?.interiors

        if (response.isSuccessful) {
            exteriors?.let { emit(it) }
            interiors?.let { emit(it) }
        } else {
            emit(emptyList())
        }
    }
}