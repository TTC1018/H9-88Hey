package com.softeer.data.repository

import com.softeer.data.datasource.SelectColorDataSource
import com.softeer.data.model.TrimCarColorDto
import kotlinx.coroutines.flow.Flow

interface SelectColorRepository {
    fun getCarColors(): Flow<List<TrimCarColorDto>>
}

class SelectColorRepositoryImpl(
    private val selectColorRemoteDataSource: SelectColorDataSource
) : SelectColorRepository {
    override fun getCarColors(): Flow<List<TrimCarColorDto>> =
        selectColorRemoteDataSource.getCarColors()
}