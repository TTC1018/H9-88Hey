package com.softeer.data.repository

import com.softeer.data.datasource.SelectOptionDataSource
import com.softeer.data.model.BasicOptionDto
import com.softeer.data.model.TrimBasicOptionDto
import com.softeer.data.model.TrimSelectOptionDto
import kotlinx.coroutines.flow.Flow

interface SelectOptionRepository {
    fun getCarCode(): Flow<String>

    fun getSelectOptions(carCode: String): Flow<List<TrimSelectOptionDto>>

    fun getHGenuines(carCode: String, selectOptions: List<String>): Flow<List<TrimSelectOptionDto>>

    fun getNPerformances(carCode: String): Flow<List<TrimSelectOptionDto>>

    fun getBasicOptions(carCode: String): Flow<List<TrimBasicOptionDto>>
}

class SelectOptionRepositoryImpl(
    val selectOptionRemoteDataSource: SelectOptionDataSource
) : SelectOptionRepository {
    override fun getCarCode(): Flow<String> =
        selectOptionRemoteDataSource.getCarCode()

    override fun getSelectOptions(carCode: String): Flow<List<TrimSelectOptionDto>> =
        selectOptionRemoteDataSource.getSelectOptions(carCode)

    override fun getHGenuines(carCode: String, selectOptions: List<String>) =
        selectOptionRemoteDataSource.getHGenuines(carCode, selectOptions)

    override fun getNPerformances(carCode: String): Flow<List<TrimSelectOptionDto>> =
        selectOptionRemoteDataSource.getNPerformances(carCode)

    override fun getBasicOptions(carCode: String): Flow<List<TrimBasicOptionDto>> =
        selectOptionRemoteDataSource.getBasicOptions(carCode)
}