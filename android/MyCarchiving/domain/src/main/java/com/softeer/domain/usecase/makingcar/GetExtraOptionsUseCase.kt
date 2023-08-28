package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectOptionRepository
import javax.inject.Inject

class GetExtraOptionsUseCase @Inject constructor(
    private val selectOptionRepository: SelectOptionRepository
) {
    operator fun invoke(carCode: String) =
        selectOptionRepository.getSelectOptions(carCode)
}