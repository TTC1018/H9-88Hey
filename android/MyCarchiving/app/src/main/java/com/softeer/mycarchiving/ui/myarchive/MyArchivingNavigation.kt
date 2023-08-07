package com.softeer.mycarchiving.ui.myarchive

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination

fun NavController.navigateToMyArchiving(navOptions: NavOptions? = null) {
    navigate(MainDestination.MY_ARCHIVING.route, navOptions)
}

fun NavGraphBuilder.myArchivingScreen(
    onBackClick: () -> Unit
) {
    composable(route = MainDestination.MY_ARCHIVING.route) {
        MyArchivingRoute(
            onBackClick = onBackClick
        )
    }
}