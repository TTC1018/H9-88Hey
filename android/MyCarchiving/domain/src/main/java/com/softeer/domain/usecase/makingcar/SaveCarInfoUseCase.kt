package com.softeer.domain.usecase.makingcar

import com.softeer.domain.model.CarInfo
import com.softeer.domain.repository.MyArchiveRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveCarInfoUseCase @Inject constructor(
    private val myArchiveRepository: MyArchiveRepository
) {
    operator fun invoke(carInfo: CarInfo): Flow<String> =
        myArchiveRepository.saveCarInfo(carInfo)
}