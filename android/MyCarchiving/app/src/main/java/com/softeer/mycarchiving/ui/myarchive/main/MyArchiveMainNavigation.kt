package com.softeer.mycarchiving.ui.myarchive.main

import androidx.activity.compose.BackHandler
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MyArchiveDestinations

fun NavController.navigateToMyArchiveMain(navOptions: NavOptions? = null) {
    navigate(MyArchiveDestinations.MY_ARCHIVE_MAIN.route, navOptions)
}

fun NavGraphBuilder.myArchiveMainScreen(
    viewModelStoreOwner: ViewModelStoreOwner?,
    moveDetailPage: (Int?, MyArchiveDestinations?) -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = MyArchiveDestinations.MY_ARCHIVE_MAIN.route
    ) {
        BackHandler {
            onBackClick()
        }
        MyArchiveMainRoute(
            viewModelStoreOwner = viewModelStoreOwner,
            onMadeCarClick = {
                moveDetailPage(
                    MY_ARCHIVE_MADE,
                    MyArchiveDestinations.MY_ARCHIVE_DETAIL
                )
            },
            onSavedCarClick = {
                moveDetailPage(
                    MY_ARCHIVE_SAVE,
                    MyArchiveDestinations.MY_ARCHIVE_DETAIL
                )
            }
        )
    }
}