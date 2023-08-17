package com.softeer.mycarchiving.ui.makingcar.selecttrim

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

fun NavController.navigateToSelectTrim(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_TRIM.route, navOptions)
}

fun NavGraphBuilder.selectTrimScreen(
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    onBackProgress: () -> Unit
) {
    composable(route = MakingCarDestinations.SELECT_TRIM.route) {
        BackHandler {
            onBackProgress()
        }

        SelectTrimRoute(
            screenProgress = screenProgress,
            viewModelOwner = viewModelStoreOwner,
        )
    }
}