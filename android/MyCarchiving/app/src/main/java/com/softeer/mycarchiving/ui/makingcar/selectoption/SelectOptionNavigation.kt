package com.softeer.mycarchiving.ui.makingcar.selectoption

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToSelectOption(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_OPTION.route, navOptions)
}

fun NavGraphBuilder.selectOptionScreen(
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit,
) {
    composable(
        route = MakingCarDestinations.SELECT_OPTION.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { 0 }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        BackHandler {
            onBackProgress()
        }

        SelectOptionRoute(
            screenProgress = screenProgress,
            viewModelStoreOwner = viewModelStoreOwner
        )
    }
}