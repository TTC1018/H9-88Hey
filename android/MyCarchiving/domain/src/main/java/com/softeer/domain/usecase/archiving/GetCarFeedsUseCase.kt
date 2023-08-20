package com.softeer.domain.usecase.archiving

import androidx.paging.PagingData
import com.softeer.domain.model.CarFeed
import com.softeer.domain.repository.ArchivingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarFeedsUseCase @Inject constructor(
    private val archivingRepository: ArchivingRepository,
) {
    operator fun invoke(selectOptions: List<String>): Flow<PagingData<CarFeed>> =
        archivingRepository.getCarFeeds(selectOptions)
}