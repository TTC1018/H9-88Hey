package com.softeer.domain.usecase.archiving

import com.softeer.domain.repository.ArchivingRepository
import javax.inject.Inject

class GetAbleOptionsUseCase @Inject constructor(
    private val archivingRepository: ArchivingRepository
) {
    operator fun invoke() =
        archivingRepository.getSelectOptions()
}