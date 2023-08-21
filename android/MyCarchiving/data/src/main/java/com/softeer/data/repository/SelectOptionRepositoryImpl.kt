package com.softeer.data.repository

import com.softeer.data.datasource.SelectOptionDataSource
import com.softeer.data.mapper.asEntity
import com.softeer.data.model.TrimBasicOptionDto
import com.softeer.data.model.TrimSelectOptionDto
import com.softeer.domain.model.CarBasicOption
import com.softeer.domain.model.CarExtraOption
import com.softeer.domain.repository.SelectOptionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SelectOptionRepositoryImpl(
    val selectOptionRemoteDataSource: SelectOptionDataSource
) : SelectOptionRepository {
    override fun getCarCode(): Flow<String> =
        selectOptionRemoteDataSource.getCarCode()

    override fun getSelectOptions(carCode: String): Flow<List<CarExtraOption>> =
        selectOptionRemoteDataSource.getSelectOptions(carCode)
            .map { it.map(TrimSelectOptionDto::asEntity) }

    override fun getHGenuines(carCode: String, selectOptions: List<String>) =
        selectOptionRemoteDataSource.getHGenuines(carCode, selectOptions)
            .map { it.map(TrimSelectOptionDto::asEntity) }

    override fun getNPerformances(carCode: String): Flow<List<CarExtraOption>> =
        selectOptionRemoteDataSource.getNPerformances(carCode)
            .map { it.map(TrimSelectOptionDto::asEntity) }

    override fun getBasicOptions(carCode: String): Flow<List<CarBasicOption>> =
        selectOptionRemoteDataSource.getBasicOptions(carCode)
            .map { it.map(TrimBasicOptionDto::asEntity) }

    override fun getTagsOfSelectOption(id: String): Flow<List<String>> =
        selectOptionRemoteDataSource.getTagsOfOption(id)
}