package com.softeer.domain.repository

import com.softeer.domain.model.CarBasicOption
import com.softeer.domain.model.CarExtraOption
import kotlinx.coroutines.flow.Flow

interface SelectOptionRepository {
    fun getCarCode(): Flow<String>

    fun getSelectOptions(carCode: String): Flow<List<CarExtraOption>>

    fun getHGenuines(carCode: String, selectOptions: List<String>): Flow<List<CarExtraOption>>

    fun getNPerformances(carCode: String): Flow<List<CarExtraOption>>

    fun getBasicOptions(carCode: String): Flow<List<CarBasicOption>>
}