package com.softeer.data.repository

import com.softeer.data.datasource.SelectColorDataSource
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.TrimExteriorCarColor
import com.softeer.data.model.TrimInteriorCarColor
import com.softeer.domain.model.CarColor
import com.softeer.domain.repository.SelectColorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SelectColorRepositoryImpl(
    private val selectColorRemoteDataSource: SelectColorDataSource
) : SelectColorRepository {
    override fun getCarColors(): Flow<List<CarColor>> =
        selectColorRemoteDataSource.getCarColors()
            .map {
                it.map { color ->
                    when (color) {
                        is TrimExteriorCarColor -> color.asEntity()
                        is TrimInteriorCarColor -> color.asEntity()
                    }
                }
            }
}