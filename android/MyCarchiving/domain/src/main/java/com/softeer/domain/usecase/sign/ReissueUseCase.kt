package com.softeer.domain.usecase.sign

import com.softeer.domain.model.Token
import com.softeer.domain.repository.SignRepository
import javax.inject.Inject

class ReissueUseCase @Inject constructor(
    private val signRepository: SignRepository,
) {
    suspend operator fun invoke(): Token? =
        signRepository.reissue()
}