package com.softeer.mycarchiving.ui.makingcar.selecttrim

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToSelectTrim(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_TRIM.route, navOptions)
}

fun NavGraphBuilder.selectTrimScreen(
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit
) {
    composable(
        route = MakingCarDestinations.SELECT_TRIM.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        BackHandler {
            onBackProgress()
        }

        SelectTrimRoute(
            screenProgress = screenProgress,
            viewModelOwner = viewModelStoreOwner,
        )
    }
}