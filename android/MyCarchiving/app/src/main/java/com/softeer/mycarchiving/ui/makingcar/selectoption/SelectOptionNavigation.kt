package com.softeer.mycarchiving.ui.makingcar.selectoption

import androidx.compose.runtime.getValue
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

fun NavGraphBuilder.selectOptionScreen(appState: HyundaiAppState) {
    composable(route = MakingCarDestinations.SELECT_OPTION.route) {
        val screenProgress by appState.currentProgressChildId.collectAsStateWithLifecycle()

        SelectOptionRoute(screenProgress = screenProgress)
    }
}