package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectTrimRepository
import javax.inject.Inject

class GetWheelDrivesUseCase @Inject constructor(
    private val selectTrimRepository: SelectTrimRepository
) {
    operator fun invoke() =
        selectTrimRepository.getWheelDrives()
}