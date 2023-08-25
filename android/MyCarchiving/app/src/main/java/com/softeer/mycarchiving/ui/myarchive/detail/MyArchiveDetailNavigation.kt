package com.softeer.mycarchiving.ui.myarchive.detail

import androidx.activity.compose.BackHandler
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softeer.mycarchiving.navigation.MyArchiveDestinations
import com.softeer.mycarchiving.ui.myarchive.KEY_MYARCHIVE_DETAIL

fun NavController.navigateToMyArchiveDetail(screenIndex: Int, navOptions: NavOptions? = null) {
    navigate("${MyArchiveDestinations.MY_ARCHIVE_DETAIL.route}/$screenIndex", navOptions)
}

fun NavGraphBuilder.myArchiveDetailScreen(
    viewModelStoreOwner: ViewModelStoreOwner?,
    onBackClick: () -> Unit,
) {
    composable(
        route = "${MyArchiveDestinations.MY_ARCHIVE_DETAIL.route}/{${KEY_MYARCHIVE_DETAIL}}",
        arguments = listOf(navArgument(KEY_MYARCHIVE_DETAIL) { type = NavType.IntType })
    ) {
        BackHandler {
            onBackClick()
        }
        MyArchiveDetailRoute(viewModelStoreOwner = viewModelStoreOwner)
    }
}