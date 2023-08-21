package com.softeer.domain.usecase.sign

import com.softeer.domain.model.Token
import com.softeer.domain.repository.SignRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signRepository: SignRepository,
) {
    operator fun invoke(email: String, password: String): Flow<Token> =
        signRepository.singIn(email, password)
}