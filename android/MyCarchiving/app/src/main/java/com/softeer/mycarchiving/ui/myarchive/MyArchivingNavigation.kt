package com.softeer.mycarchiving.ui.myarchive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MyArchiveDestinations.*
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.component.MyArchiveNavigateBar
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
        Scaffold(
            modifier = Modifier,
            topBar = {
                 MyArchiveNavigateBar(onStartAreaClick = appState.navController::popBackStack)
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
                    myArchiveDetailScreen(
                        onBackClick = appState.myArchiveNavController::popBackStack
                    )
                }
            }
        }
    }
}