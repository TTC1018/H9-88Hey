package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectColorRepository
import javax.inject.Inject

class GetCarColorsUseCase @Inject constructor(
    private val selectColorRepository: SelectColorRepository
) {
    operator fun invoke(trimId: Int) =
        selectColorRepository.getCarColors(trimId)
}