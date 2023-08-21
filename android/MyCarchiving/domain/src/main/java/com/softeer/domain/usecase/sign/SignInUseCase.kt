package com.softeer.domain.usecase.sign

import com.softeer.domain.model.Token
import com.softeer.domain.repository.SignRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signRepository: SignRepository,
) {
    suspend operator fun invoke(email: String, password: String): Token? =
        signRepository.singIn(email, password)
}