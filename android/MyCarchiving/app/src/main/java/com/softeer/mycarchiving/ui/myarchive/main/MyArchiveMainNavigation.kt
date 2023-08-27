package com.softeer.mycarchiving.ui.myarchive.main

import androidx.activity.compose.BackHandler
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.enums.MyArchivePage
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MyArchiveDestinations

fun NavController.navigateToMyArchiveMain(navOptions: NavOptions? = null) {
    navigate(MyArchiveDestinations.MY_ARCHIVE_MAIN.route, navOptions)
}

fun NavGraphBuilder.myArchiveMainScreen(
    viewModelStoreOwner: ViewModelStoreOwner?,
    moveDetailPage: (MyArchivePage?, String, MyArchiveDestinations?) -> Unit,
    moveMakingCar: (MainDestination, String?, ArchiveFeedUiModel?) -> Unit,
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
            moveCarDetail = moveDetailPage,
            moveMakingCar = moveMakingCar,
        )
    }
}