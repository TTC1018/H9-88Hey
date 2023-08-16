package com.softeer.mycarchiving.ui.loading

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
    composable(
        route = MainDestination.LOADING.route,
        enterTransition = { scaleIn() },
        exitTransition = { scaleOut() }
    ) {
        LoadingRoute(onLoading = onLoading)
    }
}