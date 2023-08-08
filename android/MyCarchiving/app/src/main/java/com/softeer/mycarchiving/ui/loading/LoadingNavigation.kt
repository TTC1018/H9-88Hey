package com.softeer.mycarchiving.ui.loading

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination

fun NavController.navigateToLoading(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.LOADING.route,
        navOptions
    )
}

fun NavGraphBuilder.loadingScreen(
    onLoading: () -> Unit
) {
    composable(route = MainDestination.LOADING.route) {
        LoadingRoute(onLoading = onLoading)
    }
}