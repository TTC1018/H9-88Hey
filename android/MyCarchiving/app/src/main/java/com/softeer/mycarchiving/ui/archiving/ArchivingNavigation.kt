package com.softeer.mycarchiving.ui.archiving

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.softeer.mycarchiving.navigation.ArchivingDestinations
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.archiving.archivingdetail.archivingDetailScreen

fun NavController.navigateToArchive(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.ARCHIVING.route,
        navOptions
    )
}

fun NavGraphBuilder.makingArchiveGraph(appState: HyundaiAppState) {
    navigation(startDestination = ArchivingDestinations.ARCHIVING_MAIN.route, route = MainDestination.ARCHIVING.route) {
        archiveScreen(appState = appState)
        archivingDetailScreen(appState = appState)
    }
}

fun NavGraphBuilder.archiveScreen(appState: HyundaiAppState) {
    composable(route = ArchivingDestinations.ARCHIVING_MAIN.route) {
        ArchiveRoute(appState = appState)
    }
}