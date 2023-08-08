package com.softeer.mycarchiving.ui.makingcar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination

fun NavController.navigateToMakingCar(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.MAKING_CAR.route,
        navOptions
    )
}

fun NavGraphBuilder.makingCarScreen(
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit,
) {
    composable(route = MainDestination.MAKING_CAR.route) {
        MakingCarRoute(
            onBackClick = onBackClick,
            onArchiveClick = onArchiveClick,
        )
    }
}