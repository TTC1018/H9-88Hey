package com.softeer.mycarchiving.model.login

sealed interface LoginUiState {
    object Loading: LoginUiState
    object Success: LoginUiState
    object Pending: LoginUiState
}