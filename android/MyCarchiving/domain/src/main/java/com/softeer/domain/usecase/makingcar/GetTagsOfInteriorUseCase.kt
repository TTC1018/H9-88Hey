package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectColorRepository
import javax.inject.Inject

class GetTagsOfInteriorUseCase @Inject constructor(
    private val selectColorRepository: SelectColorRepository,
) {
    operator fun invoke(colorId: Int) =
        selectColorRepository.getTagsOfInterior(colorId)
}