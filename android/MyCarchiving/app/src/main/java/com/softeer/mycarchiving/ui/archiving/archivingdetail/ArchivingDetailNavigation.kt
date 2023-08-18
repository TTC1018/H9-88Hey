package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.ArchivingDestinations

fun NavController.navigateToArchivingDetail(navOptions: NavOptions? = null) {
    navigate(ArchivingDestinations.ARCHIVING_DETAIL.route, navOptions)
}

fun NavGraphBuilder.archivingDetailScreen(onBackClick: () -> Unit) {
    composable(
        route = ArchivingDestinations.ARCHIVING_DETAIL.route
    ) {
        BackHandler {
            onBackClick()
        }
        ArchiveDetailRoute()
    }
}