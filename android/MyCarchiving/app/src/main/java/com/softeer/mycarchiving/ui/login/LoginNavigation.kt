package com.softeer.mycarchiving.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(MainDestination.LOGIN.route, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onLogin: () -> Unit
) {
    composable(route = MainDestination.LOGIN.route) {
        LoginRoute(
            onLogin = onLogin
        )
    }
}