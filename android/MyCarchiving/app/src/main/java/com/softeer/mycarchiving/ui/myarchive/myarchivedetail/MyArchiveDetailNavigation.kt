package com.softeer.mycarchiving.ui.myarchive.myarchivedetail

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MyArchiveDestinations

fun NavController.navigateToMyArchiveDetail(navOptions: NavOptions? = null) {
    navigate(MyArchiveDestinations.MY_ARCHIVE_DETAIL.route, navOptions)
}

fun NavGraphBuilder.myArchiveDetailScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = MyArchiveDestinations.MY_ARCHIVE_DETAIL.route
    ) {
        BackHandler {
            onBackClick()
        }
        MyArchiveDetailRoute()
    }
}