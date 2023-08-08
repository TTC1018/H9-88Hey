package com.softeer.mycarchiving.ui.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        modifier = modifier,
        onLogin = onLogin,
    )
}

@Composable
internal fun LoginScreen(
    modifier: Modifier,
    onLogin: () -> Unit,
) {
    Button(onClick = { onLogin() }) {
        Text(text = "Login")
    }
}