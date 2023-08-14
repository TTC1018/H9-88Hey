package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.ArchivingDestinations

fun NavController.navigateToArchivingDetail(navOptions: NavOptions? = null) {
    navigate(ArchivingDestinations.ARCHIVING_DETAIL.route, navOptions)
}

fun NavGraphBuilder.archivingDetailScreen() {
    composable(route = ArchivingDestinations.ARCHIVING_DETAIL.route) {
        ArchiveDetailRoute()
    }
}