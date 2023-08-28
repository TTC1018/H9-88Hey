package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectOptionRepository
import javax.inject.Inject

class GetBasicOptionsUseCase @Inject constructor(
    private val selectOptionRepository: SelectOptionRepository
) {
    operator fun invoke(carCode: String) =
        selectOptionRepository.getBasicOptions(carCode)
}