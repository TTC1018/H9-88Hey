package com.softeer.mycarchiving.ui.archiving

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination

fun NavController.navigateToArchive(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.ARCHIVING.route,
        navOptions
    )
}

fun NavGraphBuilder.archiveScreen(
) {
    composable(route = MainDestination.ARCHIVING.route) {
        ArchiveRoute()
    }
}