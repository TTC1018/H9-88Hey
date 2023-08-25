package com.softeer.domain.repository

import androidx.paging.PagingData
import com.softeer.domain.model.CarDetails
import com.softeer.domain.model.CarFeed
import com.softeer.domain.model.SelectSimpleOption
import kotlinx.coroutines.flow.Flow

interface ArchivingRepository {

    fun getSelectOptions(): Flow<List<SelectSimpleOption>>

    fun getCarFeeds(selectOptions: List<String>): Flow<PagingData<CarFeed>>

    fun getCarDetails(feedId: String): Flow<CarDetails?>

}