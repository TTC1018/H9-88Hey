package com.softeer.mycarchiving.ui.archiving.archivingmain

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.ArchivingDestinations

fun NavController.navigateToArchiveMain(navOptions: NavOptions? = null) {
    navigate(ArchivingDestinations.ARCHIVING_MAIN.route, navOptions)
}

fun NavGraphBuilder.archiveMainScreen(
    moveDetailPage: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = ArchivingDestinations.ARCHIVING_MAIN.route
    ) {
        BackHandler {
            onBackClick()
        }
        ArchiveRoute(moveDetailPage = moveDetailPage)
    }
}