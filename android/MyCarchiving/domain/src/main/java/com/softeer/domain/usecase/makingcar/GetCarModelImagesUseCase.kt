package com.softeer.domain.usecase.makingcar

import com.softeer.domain.repository.SelectTrimRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarModelImagesUseCase @Inject constructor(
    private val selectTrimRepository: SelectTrimRepository
) {
    operator fun invoke(): Flow<List<String>> =
        selectTrimRepository.getCarImageUrls()
}