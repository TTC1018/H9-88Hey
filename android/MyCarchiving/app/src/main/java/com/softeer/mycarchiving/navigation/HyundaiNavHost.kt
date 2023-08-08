package com.softeer.mycarchiving.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.archiving.archiveScreen
import com.softeer.mycarchiving.ui.archiving.navigateToArchive
import com.softeer.mycarchiving.ui.loading.loadingScreen
import com.softeer.mycarchiving.ui.loading.navigateToLoading
import com.softeer.mycarchiving.ui.login.loginScreen
import com.softeer.mycarchiving.ui.makingcar.makingCarGraph
import com.softeer.mycarchiving.ui.makingcar.navigateToMakingCar
import com.softeer.mycarchiving.ui.myarchive.myArchivingScreen
import com.softeer.mycarchiving.ui.myarchive.navigateToMyArchiving

@Composable
fun HyundaiNavHost(
    modifier: Modifier = Modifier,
    appState: HyundaiAppState,
    startDestination: String = MainDestination.LOGIN.route,
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        loginScreen(onLogin = navController::navigateToLoading)
        loadingScreen(onLoading = navController::navigateToMakingCar)
        makingCarGraph()
        archiveScreen(
            onBackClick = navController::popBackStack,
            onMyCarchiveClick = navController::navigateToMyArchiving
        )
        myArchivingScreen(
            onBackClick = navController::popBackStack
        )
    }
}