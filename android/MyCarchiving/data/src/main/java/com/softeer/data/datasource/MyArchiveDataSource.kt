package com.softeer.data.datasource

import android.util.Log
import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarTempInfoBody
import com.softeer.data.network.MyArchiveNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MyArchiveDataSource {

    fun saveTempCarInfo(body: CarTempInfoBody): Flow<String>

    fun saveCarInfo(body: CarInfoBody): Flow<String>

}

private val TAG = MyArchiveDataSource::class.simpleName

class MyArchiveRemoteDataSource(
    private val myArchiveNetworkApi: MyArchiveNetworkApi
): MyArchiveDataSource {

    override fun saveTempCarInfo(body: CarTempInfoBody): Flow<String> = flow {
        val response = myArchiveNetworkApi.saveMyCarTempInfo(body)
        val myArchiveId = response.body()?.data?.myChivingId

        if (response.isSuccessful && myArchiveId != null) {
            emit(myArchiveId)
        } else {
            emit("")
        }
    }

    override fun saveCarInfo(body: CarInfoBody): Flow<String> = flow {
        val response = myArchiveNetworkApi.saveMyCarInfo(body)
        val myArchiveId = response.body()?.data?.myChivingId

        if (response.isSuccessful && myArchiveId != null) {
            emit(myArchiveId)
        } else {
            emit("")
        }
    }
}