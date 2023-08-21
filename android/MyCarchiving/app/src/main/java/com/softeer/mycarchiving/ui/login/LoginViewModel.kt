package com.softeer.mycarchiving.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _typedEmail = MutableStateFlow("")
    val typedEmail: StateFlow<String> = _typedEmail

    private val _typedPassword = MutableStateFlow("")
    val typedPassword: StateFlow<String> = _typedPassword

    val errorMessage = MutableStateFlow(ERROR_NONE)

    fun typeEmail(text: String) {
        _typedEmail.value = text
    }

    fun typePassword(text: String) {
        _typedPassword.value = text
    }

    private fun isValidEmail(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(_typedEmail.value).matches()
    }

    fun login(): Boolean {
        if (_typedEmail.value.isEmpty()) {
            errorMessage.value = EMAIL_EMPTY_ERROR
            return false
        }
        if (!isValidEmail()) {
            errorMessage.value = EMAIL_VALID_ERROR
            return false
        }
        if (_typedPassword.value.isEmpty()) {
            errorMessage.value = PASSWORD_EMPTY_ERROR
            return false
        }
        errorMessage.value = ERROR_NONE
        return true
    }

    companion object {
        const val ERROR_NONE = ""
        const val EMAIL_EMPTY_ERROR = "이메일을 입력해주세요"
        const val PASSWORD_EMPTY_ERROR = "비밀번호를 입력해주세요"
        const val EMAIL_VALID_ERROR = "올바른 이메일 형식으로 입력해주세요"
    }
}