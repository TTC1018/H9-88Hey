package com.softeer.domain.usecase.makingcar

import com.softeer.domain.model.TrimOption
import com.softeer.domain.repository.SelectTrimRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEngineOptionsUseCase @Inject constructor (
    private val selectTrimRepository: SelectTrimRepository
) {
    operator fun invoke(): Flow<List<TrimOption>> =
        selectTrimRepository.getEngines()
}