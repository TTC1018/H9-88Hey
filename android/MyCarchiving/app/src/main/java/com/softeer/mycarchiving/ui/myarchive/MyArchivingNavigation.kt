package com.softeer.mycarchiving.ui.myarchive

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
import androidx.navigation.compose.rememberNavController
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MyArchiveDestinations.*
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.component.MyArchiveNavigateBar
import com.softeer.mycarchiving.ui.makingcar.navigateToMakingCar
import com.softeer.mycarchiving.ui.myarchive.detail.myArchiveDetailScreen
import com.softeer.mycarchiving.ui.myarchive.main.myArchiveMainScreen

fun NavController.navigateToMyArchiving(navOptions: NavOptions? = null) {
    navigate(MainDestination.MY_ARCHIVING.route, navOptions)
}

fun NavGraphBuilder.makingMyArchiveGraph(
    appState: HyundaiAppState
) {
    composable(
        route = MainDestination.MY_ARCHIVING.route
    ) {
        appState.myArchiveNavController = rememberNavController()

        val myArchiveViewModelOwner: ViewModelStoreOwner = remember(it) {
            appState.navController.getBackStackEntry(MainDestination.MY_ARCHIVING.route)
        }

        val onStartAreaClick: () -> Unit =
            if (appState.currentMyArchiveDestinations == MY_ARCHIVE_MAIN)
                appState.navController::navigateToMakingCar
            else
                appState.myArchiveNavController::popBackStack

        Scaffold(
            modifier = Modifier,
            topBar = {
                MyArchiveNavigateBar(onStartAreaClick = onStartAreaClick)
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NavHost(
                    navController = appState.myArchiveNavController,
                    startDestination = MY_ARCHIVE_MAIN.route
                ) {
                    myArchiveMainScreen(
                        viewModelStoreOwner = myArchiveViewModelOwner,
                        moveDetailPage = appState::navigateToMyArchiveDestination,
                        onBackClick = appState.navController::popBackStack
                    )
                    myArchiveDetailScreen(
                        viewModelStoreOwner = myArchiveViewModelOwner,
                        onBackClick = appState.myArchiveNavController::popBackStack,
                        onMakingCarClick = appState::navigateToMainDestination
                    )
                }
            }
        }
    }
}