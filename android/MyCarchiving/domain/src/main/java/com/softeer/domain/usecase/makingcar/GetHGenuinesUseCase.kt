package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectOptionRepository
import javax.inject.Inject

class GetHGenuinesUseCase @Inject constructor(
    private val selectOptionRepository: SelectOptionRepository
) {
    operator fun invoke(carCode: String, extraOptions: List<String>) =
        selectOptionRepository.getHGenuines(carCode, extraOptions)
}