package com.softeer.mycarchiving.ui.makingcar.selectoption

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
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_BODY
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_ENGINE
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_TRIM
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_WHEEL

fun NavController.navigateToSelectOption(
    trimId: Int,
    engineId: Int,
    bodyTypeId: Int,
    wheelId: Int,
    navOptions: NavOptions? = null
) {
    navigate(
        "${MakingCarDestinations.SELECT_OPTION.route}/$trimId/$engineId/$bodyTypeId/$wheelId",
        navOptions
    )
}

fun NavGraphBuilder.selectOptionScreen(
    mainProgress: Int,
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit,
) {
    composable(
        route = "${MakingCarDestinations.SELECT_OPTION.route}/{$KEY_SELECT_OPTION_TRIM}/{$KEY_SELECT_OPTION_ENGINE}/{$KEY_SELECT_OPTION_BODY}/{$KEY_SELECT_OPTION_WHEEL}",
        arguments = listOf(
            navArgument(KEY_SELECT_OPTION_TRIM) { type = NavType.IntType },
            navArgument(KEY_SELECT_OPTION_ENGINE) { type = NavType.IntType },
            navArgument(KEY_SELECT_OPTION_BODY) { type = NavType.IntType },
            navArgument(KEY_SELECT_OPTION_WHEEL) { type = NavType.IntType }
        ),
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        BackHandler {
            onBackProgress()
        }

        SelectOptionRoute(
            mainProgress = mainProgress,
            screenProgress = screenProgress,
            viewModelStoreOwner = viewModelStoreOwner
        )
    }
}