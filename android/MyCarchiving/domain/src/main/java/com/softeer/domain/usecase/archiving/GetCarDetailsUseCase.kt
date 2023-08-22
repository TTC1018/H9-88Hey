package com.softeer.domain.usecase.archiving

import com.softeer.domain.model.CarDetails
import com.softeer.domain.repository.ArchivingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarDetailsUseCase @Inject constructor(
    private val archivingRepository: ArchivingRepository
) {
    operator fun invoke(feedId: Long): Flow<CarDetails?> =
        archivingRepository.getCarDetails(feedId)
}