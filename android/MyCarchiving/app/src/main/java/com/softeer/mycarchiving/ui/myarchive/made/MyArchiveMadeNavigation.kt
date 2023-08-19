package com.softeer.mycarchiving.ui.myarchive.made

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MyArchiveDestinations

fun NavController.navigateToMyArchiveMade(navOptions: NavOptions? = null) {
    navigate(MyArchiveDestinations.MY_ARCHIVE_MADE.route, navOptions)
}

fun NavGraphBuilder.myArchiveMadeScreen(
    moveDetailPage: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = MyArchiveDestinations.MY_ARCHIVE_MADE.route
    ) {
        BackHandler {
            onBackClick()
        }
        MyArchiveMadeRoute()
    }
}