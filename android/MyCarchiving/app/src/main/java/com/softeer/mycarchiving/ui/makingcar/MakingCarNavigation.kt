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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.archiving.KEY_ARCHIVE_FEED_ID
import com.softeer.mycarchiving.ui.component.MakeCarBottomBar
import com.softeer.mycarchiving.ui.component.MakeCarTopBar
import com.softeer.mycarchiving.ui.makingcar.complete.completeScreen
import com.softeer.mycarchiving.ui.makingcar.selectcolor.selectColorScreen
import com.softeer.mycarchiving.ui.makingcar.selectmodel.selectModelScreen
import com.softeer.mycarchiving.ui.makingcar.selectoption.selectOptionScreen
import com.softeer.mycarchiving.ui.makingcar.selecttrim.selectTrimScreen

fun NavController.navigateToMakingCar(feedId: Long? = null, navOptions: NavOptions? = null) {
    if (feedId != null)
        navigate("${MainDestination.MAKING_CAR.route}?$KEY_ARCHIVE_FEED_ID=$feedId", navOptions)
    else
        navigate(MainDestination.MAKING_CAR.route, navOptions)
}

fun NavGraphBuilder.makingCarGraph(
    appState: HyundaiAppState,
) {
    composable(
        route = "${MainDestination.MAKING_CAR.route}?$KEY_ARCHIVE_FEED_ID={$KEY_ARCHIVE_FEED_ID}",
        arguments = listOf(navArgument(KEY_ARCHIVE_FEED_ID) { nullable = true }),
    ) {
        appState.makingCarNavController = rememberNavController()
        val mainProgress = appState.currentProgressId
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
                        mainProgress = mainProgress,
                        screenProgress = screenProgress,
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress
                    )
                    selectColorScreen(
                        mainProgress = mainProgress,
                        screenProgress = screenProgress,
                        viewModelStoreOwner = makingCarViewModelOwner,
                        onBackProgress = appState::onBackProgress
                    )
                    selectOptionScreen(
                        mainProgress = mainProgress,
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