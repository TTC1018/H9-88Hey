package com.softeer.domain.usecase.makingcar

import com.softeer.domain.model.CarTempInfo
import com.softeer.domain.repository.MyArchiveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveTempCarInfoUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository
) {
    operator fun invoke(carTempInfo: CarTempInfo): Flow<Long> =
        myArchiveRepository.saveTempCarInfo(carTempInfo)
}