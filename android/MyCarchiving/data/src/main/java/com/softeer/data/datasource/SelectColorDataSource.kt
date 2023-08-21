package com.softeer.data.datasource

import com.softeer.data.model.TrimCarColorDto
import com.softeer.data.network.SelectColorNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SelectColorDataSource {
    fun getCarColors(): Flow<List<TrimCarColorDto>>


    fun getTagsOfInterior(id: Int): Flow<List<String>>

    fun getTagsOfExterior(id: Int): Flow<List<String>>
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

    override fun getTagsOfInterior(id: Int): Flow<List<String>> = flow {
        val response = selectColorNetworkApi.getTagsOfInterior(id)
        val tags = response.body()?.data?.tags

        if (response.isSuccessful && tags != null) {
            emit(tags)
        } else {
            emit(emptyList())
        }
    }

    override fun getTagsOfExterior(id: Int): Flow<List<String>> = flow {
        val response = selectColorNetworkApi.getTagsOfExterior(id)
        val tags = response.body()?.data?.tags

        if (response.isSuccessful && tags != null) {
            emit(tags)
        } else {
            emit(emptyList())
        }
    }
}