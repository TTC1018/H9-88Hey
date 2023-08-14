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
import com.softeer.mycarchiving.ui.makingcar.complete.navigateToComplete
import com.softeer.mycarchiving.ui.makingcar.navigateToMakingCar
import com.softeer.mycarchiving.ui.makingcar.selectcolor.navigateToSelectColor
import com.softeer.mycarchiving.ui.makingcar.selectoption.navigateToSelectOption
import com.softeer.mycarchiving.ui.makingcar.selecttrim.navigateToSelectTrim
import com.softeer.mycarchiving.ui.myarchive.navigateToMyArchiving
import com.softeer.mycarchiving.util.MakeCarProcess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

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
    private val currentDestination: NavDestination?
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

    val currentProgressId = MutableStateFlow(0)
    val currentProgressChildId = MutableStateFlow(-1)
    val progressEnd = MutableStateFlow(false)

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

    fun navigateInMakingCar(currentMakingCarDestination: MakingCarDestinations?) {
        when(currentMakingCarDestination) {
            SELECT_MODEL -> navController.navigateToSelectTrim()
            SELECT_TRIM -> navController.navigateToSelectColor()
            SELECT_COLOR -> navController.navigateToSelectOption()
            SELECT_OPTION -> navController.navigateToComplete()
            SELECT_COMPLETE -> {/*unused*/}
            else -> {}
        }
    }

    fun onNextProgress(navigate: () -> Unit) {
        // 완성 화면일 경우
        if (progressEnd.value) {
            return
        }

        // 다음 세부 단계가 남았을 경우
        currentProgressChildId.value.let { childId ->
            if (childId < MakeCarProcess.makeCarProcess[currentProgressId.value].children.last().id) {
                if (childId < 0) navigate()
                currentProgressChildId.value = childId + 1
                return
            }
        }

        // 다음 단계가 남았을 경우
        currentProgressId.value.let { progressId ->
            if (progressId < MakeCarProcess.makeCarProcess.last().id) {
                currentProgressId.value = progressId + 1
                currentProgressChildId.value = MakeCarProcess.makeCarProcess[progressId + 1].children.first().id
            } else { // 모든 단계가 끝나는 경우
                progressEnd.value = true
            }
        }
        navigate()
    }

    fun onBackProgress() {
        // 완성 화면일 경우
        if (progressEnd.value) {
            progressEnd.value = false
            navController.popBackStack()
            return
        }

        // 이전 세부 단계가 남았을 경우
        val minChildId = if (MakeCarProcess.makeCarProcess[currentProgressId.value].needNoChild) -1 else 0
        currentProgressChildId.value.let { childId ->
            if (childId > minChildId) {
                currentProgressChildId.value = childId - 1
                if (childId - 1 < 0) navController.popBackStack()
                return
            }
        }

        // 이전 단계가 남았을 경우
        currentProgressId.value.let { progressId ->
            if (progressId > MakeCarProcess.makeCarProcess.first().id) {
                currentProgressId.value = progressId - 1
                currentProgressChildId.value = MakeCarProcess.makeCarProcess[progressId - 1].children.last().id
            } else { // 이전 단계가 없는 경우
                return
            }
        }
        navController.popBackStack()
    }

}