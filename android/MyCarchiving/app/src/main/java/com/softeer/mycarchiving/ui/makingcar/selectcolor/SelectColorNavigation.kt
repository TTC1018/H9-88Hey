package com.softeer.mycarchiving.ui.makingcar.selectcolor

import androidx.compose.runtime.getValue
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
    appState: HyundaiAppState
) {
    composable(route = MakingCarDestinations.SELECT_COLOR.route) {
        val screenProgress by appState.currentProgressChildId.collectAsStateWithLifecycle()

        SelectColorRoute(
            screenProgress = screenProgress
        )
    }
}