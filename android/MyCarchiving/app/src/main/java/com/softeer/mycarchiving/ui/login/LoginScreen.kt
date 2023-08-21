package com.softeer.mycarchiving.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.component.AlertMessage
import com.softeer.mycarchiving.ui.component.HyundaiButton
import com.softeer.mycarchiving.ui.component.HyundaiLogo
import com.softeer.mycarchiving.ui.component.LoginInput
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val email by loginViewModel.typedEmail.collectAsStateWithLifecycle()
    val password by loginViewModel.typedPassword.collectAsStateWithLifecycle()
    val errorMessage by loginViewModel.errorMessage.collectAsStateWithLifecycle()
    val loginSuccess by loginViewModel.loginSuccess.collectAsStateWithLifecycle()

    if (loginSuccess) {
        onLogin()
    }
    LoginScreen(
        modifier = modifier,
        emailInput = email,
        passwordInput = password,
        errorMessage = errorMessage,
        typeEmail = loginViewModel::typeEmail,
        typePassword = loginViewModel::typePassword,
        onLogin = loginViewModel::login,
    )
}

@Composable
internal fun LoginScreen(
    modifier: Modifier,
    emailInput: String,
    passwordInput: String,
    errorMessage: String,
    typeEmail: (String) -> Unit,
    typePassword: (String) -> Unit,
    onLogin: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .clickable(
                onClick = focusManager::clearFocus,
                indication = null,
                interactionSource = MutableInteractionSource()
            )
            .padding(horizontal = 16.dp, vertical = 160.dp),
    ) {
        HyundaiLogo()
        Spacer(modifier = Modifier.height(25.dp))
        LoginInput(
            placeHolder = stringResource(id = R.string.login_email_placeholder),
            input = emailInput,
            onValueChanged = typeEmail,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(8.dp))
        LoginInput(
            placeHolder = stringResource(id = R.string.login_password_placeholder),
            input = passwordInput,
            onValueChanged = typePassword,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            onDone = onLogin
        )
        Spacer(modifier = Modifier.height(4.dp))
        AlertMessage(message = errorMessage)
        Spacer(modifier = Modifier.height(12.dp))
        HyundaiButton(text = stringResource(id = R.string.login), onClick = onLogin)
    }
}

@Preview
@Composable
fun PreviewLoginRoute() {
    LoginRoute(onLogin = {})
}