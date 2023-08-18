package com.softeer.mycarchiving.ui.makingcar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.component.MakeCarBottomBar
import com.softeer.mycarchiving.ui.component.MakeCarTopBar
import com.softeer.mycarchiving.ui.makingcar.complete.completeScreen
import com.softeer.mycarchiving.ui.makingcar.selectcolor.selectColorScreen
import com.softeer.mycarchiving.ui.makingcar.selectmodel.selectModelScreen
import com.softeer.mycarchiving.ui.makingcar.selectoption.selectOptionScreen
import com.softeer.mycarchiving.ui.makingcar.selecttrim.selectTrimScreen

fun NavController.navigateToMakingCar(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.MAKING_CAR.route,
        navOptions
    )
}

fun NavGraphBuilder.makingCarGraph(
    appState: HyundaiAppState,
) {
    composable(
        route = MainDestination.MAKING_CAR.route
    ) {
        val screenProgress = appState.currentProgressChildId
        val makingCarViewModelOwner: ViewModelStoreOwner = remember(it) {
            appState.navController.getBackStackEntry(MainDestination.MAKING_CAR.route)
        }

        Scaffold(
            modifier = Modifier,
            topBar = {
                MakeCarTopBar(
                    appState = appState,
                    viewModelStoreOwner = makingCarViewModelOwner
                )
            },
            bottomBar = {
                MakeCarBottomBar(
                    appState = appState,
                    viewModelStoreOwner = makingCarViewModelOwner
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NavHost(
                    navController = appState.makingCarNavController,
                    startDestination = MakingCarDestinations.SELECT_MODEL.route
                ) {
                    selectModelScreen(
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress
                    )
                    selectTrimScreen(
                        screenProgress = screenProgress,
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress
                    )
                    selectColorScreen(
                        screenProgress = screenProgress,
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress
                    )
                    selectOptionScreen(
                        screenProgress = screenProgress,
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress
                    )
                    completeScreen(
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress,
                    )
                }
            }
        }
    }
}