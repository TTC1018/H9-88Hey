package com.softeer.data.datasource

import android.util.Log
import com.softeer.data.model.TrimHGenuineDto
import com.softeer.data.model.TrimSelectOptionDto
import com.softeer.data.network.SelectOptionNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private val TAG = SelectOptionDataSource::class.simpleName

interface SelectOptionDataSource {
    fun getCarCode(): Flow<String>

    fun getSelectOptions(carCode: String): Flow<List<TrimSelectOptionDto>>

    fun getHGenuines(carCode: String, selectOptions: List<String>): Flow<List<TrimHGenuineDto>>

    fun getNPerformances(carCode: String): Flow<List<TrimSelectOptionDto>>
}

class SelectOptionRemoteDataSource(
    private val selectOptionNetworkApi: SelectOptionNetworkApi
) : SelectOptionDataSource {
    override fun getCarCode(): Flow<String> = flow {
        val response = selectOptionNetworkApi.getCarCode()
        val carCode = response.body()?.data?.carCode

        if (response.isSuccessful && carCode != null) {
            emit(carCode)
        } else {
            emit("")
        }
    }

    override fun getSelectOptions(carCode: String): Flow<List<TrimSelectOptionDto>> = flow {
        val response = selectOptionNetworkApi.getSelectOptions(carCode)
        val selectOptions = response.body()?.data?.selectOptions

        if (response.isSuccessful && selectOptions != null) {
            emit(selectOptions)
        } else {
            emit(emptyList())
        }
    }

    override fun getHGenuines(
        carCode: String,
        selectOptions: List<String>
    ): Flow<List<TrimHGenuineDto>> = flow {
        val response = selectOptionNetworkApi.getHGenuines(carCode, selectOptions)
        val hGenuines = response.body()?.data?.selectOptions

        if (response.isSuccessful && hGenuines != null) {
            emit(hGenuines)
        } else {
            emit(emptyList())
        }
    }

    override fun getNPerformances(carCode: String): Flow<List<TrimSelectOptionDto>> = flow {
        val response = selectOptionNetworkApi.getNPerformances(carCode)
        val nPerformances = response.body()?.data?.selectOptions

        if (response.isSuccessful && nPerformances != null) {
            emit(nPerformances)
        } else {
            emit(emptyList())
        }
    }
}