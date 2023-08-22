package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softeer.mycarchiving.navigation.ArchivingDestinations
import com.softeer.mycarchiving.ui.archiving.KEY_ARCHIVE_DETAIL

fun NavController.navigateToArchivingDetail(feedId: Long, navOptions: NavOptions? = null) {
    navigate("${ArchivingDestinations.ARCHIVING_DETAIL.route}/$feedId", navOptions)
}

fun NavGraphBuilder.archivingDetailScreen(
    onBackClick: () -> Unit,
    onMakingCarClick: (Long?) -> Unit,
) {
    composable(
        route = "${ArchivingDestinations.ARCHIVING_DETAIL.route}/{$KEY_ARCHIVE_DETAIL}",
        arguments = listOf(navArgument(KEY_ARCHIVE_DETAIL) { type = NavType.LongType })
    ) {
        BackHandler {
            onBackClick()
        }
        ArchiveDetailRoute(
            onMakingCarClick = onMakingCarClick
        )
    }
}