package com.softeer.mycarchiving.ui.myarchive.myarchivesave

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MyArchiveDestinations

fun NavController.navigateToMyArchiveSave(navOptions: NavOptions? = null) {
    navigate(MyArchiveDestinations.MY_ARCHIVE_SAVE.route, navOptions)
}

fun NavGraphBuilder.myArchiveSaveScreen(
    moveDetailPage: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = MyArchiveDestinations.MY_ARCHIVE_SAVE.route
    ) {
        BackHandler {
            onBackClick()
        }
        MyArchiveSaveRoute()
    }
}