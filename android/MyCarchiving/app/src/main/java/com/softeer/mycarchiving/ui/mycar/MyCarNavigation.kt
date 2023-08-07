package com.softeer.mycarchiving.ui.mycar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination

fun NavController.navigateToMyCar(navOptions: NavOptions? = null) {
    navigate(MainDestination.MY_CAR.route, navOptions)
}

fun NavGraphBuilder.myCarScreen(
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit
) {
    composable(route = MainDestination.MY_CAR.route) {
        MyCarRoute(
            onBackClick = onBackClick,
            onArchiveClick = onArchiveClick
        )
    }
}