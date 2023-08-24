package com.softeer.mycarchiving.ui.makingcar.selectcolor

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_COLOR

fun NavController.navigateToSelectColor(trimId: Int, navOptions: NavOptions? = null) {
    navigate("${MakingCarDestinations.SELECT_COLOR.route}/$trimId", navOptions)
}

fun NavGraphBuilder.selectColorScreen(
    mainProgress: Int,
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit,
) {
    composable(
        route = "${MakingCarDestinations.SELECT_COLOR.route}/{$KEY_SELECT_COLOR}",
        arguments = listOf(navArgument(KEY_SELECT_COLOR) { type = NavType.IntType }),
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        BackHandler {
            onBackProgress()
        }

        SelectColorRoute(
            mainProgress = mainProgress,
            screenProgress = screenProgress,
            viewModelStoreOwner = viewModelStoreOwner,
        )
    }
}