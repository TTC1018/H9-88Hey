package com.softeer.mycarchiving.ui.makingcar.selectcolor

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.ui.HyundaiAppState

fun NavController.navigateToSelectColor(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_COLOR.route, navOptions)
}

fun NavGraphBuilder.selectColorScreen(
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit,
) {
    composable(route = MakingCarDestinations.SELECT_COLOR.route) {
        BackHandler {
            onBackProgress()
        }

        SelectColorRoute(
            screenProgress = screenProgress,
            viewModelStoreOwner = viewModelStoreOwner,
        )
    }
}