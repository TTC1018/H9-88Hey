package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectColorRepository
import javax.inject.Inject

class GetTagsOfExteriorUseCase @Inject constructor(
    private val selectColorRepository: SelectColorRepository
) {
    operator fun invoke(id: Int) =
        selectColorRepository.getTagsOfExterior(id)
}