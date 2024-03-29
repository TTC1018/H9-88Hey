package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectOptionRepository
import javax.inject.Inject

class GetCarCodeUseCase @Inject constructor(
    private val selectOptionRepository: SelectOptionRepository
) {
    operator fun invoke(
        trimId: Int,
        engineId: Int,
        bodyTypeId: Int,
        wheelId: Int,
    ) = selectOptionRepository.getCarCode(trimId, engineId, bodyTypeId, wheelId)
}