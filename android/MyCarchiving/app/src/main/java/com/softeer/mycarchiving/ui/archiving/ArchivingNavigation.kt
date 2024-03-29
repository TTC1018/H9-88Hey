package com.softeer.mycarchiving.ui.archiving

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
import androidx.navigation.compose.rememberNavController
import com.softeer.mycarchiving.navigation.ArchivingDestinations.*
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.archiving.archivingdetail.archivingDetailScreen
import com.softeer.mycarchiving.ui.archiving.archivingmain.archiveMainScreen
import com.softeer.mycarchiving.ui.component.ArchiveNavigateBar

fun NavController.navigateToArchive(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.ARCHIVING.route,
        navOptions
    )
}

fun NavGraphBuilder.makingArchiveGraph(
    appState: HyundaiAppState,
) {
    composable(
        route = MainDestination.ARCHIVING.route
    ) {
        appState.archivingNavController = rememberNavController()

        Scaffold(
            modifier = Modifier,
            topBar = {
                ArchiveNavigateBar(
                    onStartAreaClick = if (appState.currentArchivingDestinations == ARCHIVING_DETAIL) {
                        { appState.archivingNavController.popBackStack() }
                    } else {
                        { appState.navigateToMainDestination(MainDestination.MAKING_CAR) }
                    },
                    onEndAreaClick = { appState.navigateToMainDestination(MainDestination.MY_ARCHIVING) }
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NavHost(
                    navController = appState.archivingNavController,
                    startDestination = ARCHIVING_MAIN.route
                ) {
                    archiveMainScreen(
                        scope = appState.coroutineScope,
                        moveDetailPage = appState::navigateToArchivingDestination,
                        onBackClick = { appState.navigateToMainDestination(MainDestination.MAKING_CAR) }
                    )
                    archivingDetailScreen(
                        onBackClick = appState.archivingNavController::popBackStack,
                        onMakingCarClick = appState::navigateToMainDestination
                    )
                }
            }
        }
    }
}

