package com.softeer.mycarchiving.ui.myarchive.detail

import androidx.activity.compose.BackHandler
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MyArchiveDestinations
import com.softeer.mycarchiving.ui.myarchive.KEY_MYARCHIVE_DETAIL
import com.softeer.mycarchiving.ui.myarchive.KEY_MYARCHIVE_FEED_ID

fun NavController.navigateToMyArchiveDetail(
    screenIndex: Int,
    feedId: String,
    navOptions: NavOptions? = null
) {
    navigate("${MyArchiveDestinations.MY_ARCHIVE_DETAIL.route}/$screenIndex/$feedId", navOptions)
}

fun NavGraphBuilder.myArchiveDetailScreen(
    viewModelStoreOwner: ViewModelStoreOwner?,
    onBackClick: () -> Unit,
    onMakingCarClick: (MainDestination, String?) -> Unit,
) {
    composable(
        route = "${MyArchiveDestinations.MY_ARCHIVE_DETAIL.route}/{$KEY_MYARCHIVE_DETAIL}/{$KEY_MYARCHIVE_FEED_ID}",
        arguments = listOf(
            navArgument(KEY_MYARCHIVE_DETAIL) { type = NavType.IntType },
            navArgument(KEY_MYARCHIVE_FEED_ID) { type = NavType.StringType }
        )
    ) {
        BackHandler {
            onBackClick()
        }
        MyArchiveDetailRoute(
            viewModelStoreOwner = viewModelStoreOwner,
            onMakingCarClick = onMakingCarClick
        )
    }
}