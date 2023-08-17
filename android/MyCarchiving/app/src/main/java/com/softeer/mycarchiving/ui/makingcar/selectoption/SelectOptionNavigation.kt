package com.softeer.mycarchiving.ui.makingcar.selectoption

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

fun NavController.navigateToSelectOption(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_OPTION.route, navOptions)
}

fun NavGraphBuilder.selectOptionScreen(
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit,
) {
    composable(route = MakingCarDestinations.SELECT_OPTION.route) {
        BackHandler {
            onBackProgress()
        }

        SelectOptionRoute(
            screenProgress = screenProgress,
            viewModelStoreOwner = viewModelStoreOwner
        )
    }
}