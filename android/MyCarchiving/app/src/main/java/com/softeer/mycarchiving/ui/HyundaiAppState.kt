package com.softeer.mycarchiving.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MainDestination.*
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.navigation.MakingCarDestinations.*
import com.softeer.mycarchiving.ui.archiving.navigateToArchive
import com.softeer.mycarchiving.ui.loading.navigateToLoading
import com.softeer.mycarchiving.ui.login.navigateToLogin
import com.softeer.mycarchiving.ui.makingcar.navigateToMakingCar
import com.softeer.mycarchiving.ui.myarchive.navigateToMyArchiving
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberHyundaiAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): HyundaiAppState {
    return remember(
        navController,
    ) {
        HyundaiAppState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class HyundaiAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentMainDestination: MainDestination?
        @Composable get() = when (currentDestination?.route) {
            LOGIN.route -> LOGIN
            LOADING.route -> LOADING
            MAKING_CAR.route,
            SELECT_MODEL.route,
            SELECT_TRIM.route,
            SELECT_COLOR.route,
            SELECT_OPTION.route,
            SELECT_COMPLETE.route-> MAKING_CAR
            ARCHIVING.route -> ARCHIVING
            MY_ARCHIVING.route -> MY_ARCHIVING
            else -> null
        }

    val currentMakingCarDestinations: MakingCarDestinations?
        @Composable get() = when(currentDestination?.route) {
            SELECT_MODEL.route -> SELECT_MODEL
            SELECT_TRIM.route -> SELECT_TRIM
            SELECT_COLOR.route -> SELECT_COLOR
            SELECT_OPTION.route -> SELECT_OPTION
            SELECT_COMPLETE.route -> SELECT_COMPLETE
            else -> null
        }

    val mainDestinations: List<MainDestination> = MainDestination.values().asList()

    fun navigateToMainDestination(mainDestination: MainDestination) {
        val mainNavOptions = navOptions {
            //
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            // 같은 장소로 또 이동했을 때 백스택에 중복으로 쌓이는 것을 방지
            launchSingleTop = true

            // 이전 화면으로 돌아갔을 때 상태를 복원
            restoreState = true
        }

        when (mainDestination) {
            LOGIN -> navController.navigateToLogin(mainNavOptions)
            LOADING -> navController.navigateToLoading(mainNavOptions)
            MAKING_CAR -> navController.navigateToMakingCar(mainNavOptions)
            ARCHIVING -> navController.navigateToArchive(mainNavOptions)
            MY_ARCHIVING -> navController.navigateToMyArchiving(mainNavOptions)
            else -> {}
        }
    }

}