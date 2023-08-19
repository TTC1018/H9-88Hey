package com.softeer.domain.repository

import com.softeer.domain.model.TrimModelOption
import com.softeer.domain.model.TrimOption
import kotlinx.coroutines.flow.Flow

interface SelectTrimRepository {
    fun getCarImageUrls(): Flow<List<String>>

    fun getModels(): Flow<List<TrimModelOption>>

    fun getEngines(): Flow<List<TrimOption>>

    fun getBodyTypes(): Flow<List<TrimOption>>

    fun getWheelDrives(): Flow<List<TrimOption>>
}