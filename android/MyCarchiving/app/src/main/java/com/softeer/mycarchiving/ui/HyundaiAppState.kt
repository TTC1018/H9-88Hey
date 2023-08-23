package com.softeer.mycarchiving.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.softeer.mycarchiving.navigation.ArchivingDestinations
import com.softeer.mycarchiving.navigation.ArchivingDestinations.*
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MainDestination.*
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.navigation.MakingCarDestinations.*
import com.softeer.mycarchiving.navigation.MyArchiveDestinations
import com.softeer.mycarchiving.navigation.MyArchiveDestinations.*
import com.softeer.mycarchiving.ui.archiving.archivingdetail.navigateToArchivingDetail
import com.softeer.mycarchiving.ui.archiving.archivingmain.navigateToArchiveMain
import com.softeer.mycarchiving.ui.archiving.navigateToArchive
import com.softeer.mycarchiving.ui.login.navigateToLogin
import com.softeer.mycarchiving.ui.makingcar.complete.navigateToComplete
import com.softeer.mycarchiving.ui.makingcar.navigateToMakingCar
import com.softeer.mycarchiving.ui.makingcar.selectcolor.navigateToSelectColor
import com.softeer.mycarchiving.ui.makingcar.selectoption.navigateToSelectOption
import com.softeer.mycarchiving.ui.makingcar.selecttrim.navigateToSelectTrim
import com.softeer.mycarchiving.ui.myarchive.detail.navigateToMyArchiveDetail
import com.softeer.mycarchiving.ui.myarchive.main.navigateToMyArchiveMain
import com.softeer.mycarchiving.ui.myarchive.navigateToMyArchiving
import com.softeer.mycarchiving.util.MakeCarProcess
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberHyundaiAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    makingCarNavController: NavHostController = rememberNavController(),
    archivingNavController: NavHostController = rememberNavController(),
    myArchiveNavController: NavHostController = rememberNavController(),
): HyundaiAppState {
    return remember(
        navController,
    ) {
        HyundaiAppState(
            navController = navController,
            makingCarNavController = makingCarNavController,
            archivingNavController = archivingNavController,
            myArchiveNavController = myArchiveNavController,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class HyundaiAppState(
    val navController: NavHostController,
    var makingCarNavController: NavHostController,
    var archivingNavController: NavHostController,
    var myArchiveNavController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    private val currentMakingCarDestination: NavDestination?
        @Composable get() = makingCarNavController
            .currentBackStackEntryAsState().value?.destination

    private val currentArchivingDestination: NavDestination?
        @Composable get() = archivingNavController
            .currentBackStackEntryAsState().value?.destination

    private val currentMyArchiveDestination: NavDestination?
        @Composable get() = myArchiveNavController
            .currentBackStackEntryAsState().value?.destination

    val currentMainDestination: MainDestination?
        @Composable get() = when (val route = currentDestination?.route) {
            LOGIN.route -> LOGIN
            route?.split("?")?.first() -> MAKING_CAR
            ARCHIVING.route -> ARCHIVING
            MY_ARCHIVING.route -> MY_ARCHIVING
            DRIVER_COMMENT.route -> DRIVER_COMMENT
            CONSUMER_COMMENT.route -> CONSUMER_COMMENT
            else -> null
        }

    val currentMakingCarDestinations: MakingCarDestinations?
        @Composable get() = when (currentMakingCarDestination?.route) {
            SELECT_MODEL.route -> SELECT_MODEL
            SELECT_TRIM.route -> SELECT_TRIM
            SELECT_COLOR.route -> SELECT_COLOR
            SELECT_OPTION.route -> SELECT_OPTION
            SELECT_COMPLETE.route -> SELECT_COMPLETE
            else -> null
        }

    val currentArchivingDestinations: ArchivingDestinations?
        @Composable get() = when {
            currentArchivingDestination?.route == ARCHIVING_MAIN.route -> ARCHIVING_MAIN
            currentArchivingDestination?.route?.startsWith(ARCHIVING_DETAIL.route) == true -> ARCHIVING_DETAIL
            else -> null
        }

    val currentMyArchiveDestinations: MyArchiveDestinations?
        @Composable get() = when {
            currentMyArchiveDestination?.route == MY_ARCHIVE_MAIN.route -> MY_ARCHIVE_MAIN
            currentMyArchiveDestination?.route?.startsWith(MY_ARCHIVE_DETAIL.route) == true -> MY_ARCHIVE_DETAIL
            else -> null
        }

    val mainDestinations: List<MainDestination> = MainDestination.values().asList()

    var currentProgressId by mutableIntStateOf(0)
    var currentProgressChildId by mutableIntStateOf(-1)
    var progressEnd by mutableStateOf(false)

    fun navigateToMainDestination(mainDestination: MainDestination) {
        val mainNavOptions = navOptions {
            //
            /*popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }*/

            // 같은 장소로 또 이동했을 때 백스택에 중복으로 쌓이는 것을 방지
            launchSingleTop = true

            // 이전 화면으로 돌아갔을 때 상태를 복원
            restoreState = true
        }

        when (mainDestination) {
            LOGIN -> navController.navigateToLogin(mainNavOptions)
            MAKING_CAR -> navController.navigateToMakingCar(navOptions = mainNavOptions)
            ARCHIVING -> navController.navigateToArchive(mainNavOptions)
            MY_ARCHIVING -> navController.navigateToMyArchiving(mainNavOptions)
            else -> {}
        }
    }

    fun navigateInMakingCar(currentMakingCarDestination: MakingCarDestinations?) {
        when (currentMakingCarDestination) {
            SELECT_MODEL -> makingCarNavController.navigateToSelectTrim()
            SELECT_TRIM -> makingCarNavController.navigateToSelectColor()
            SELECT_COLOR -> makingCarNavController.navigateToSelectOption()
            SELECT_OPTION -> makingCarNavController.navigateToComplete()
            SELECT_COMPLETE -> {/*unused*/}
            else -> {}
        }
    }

    fun navigateToArchivingDestination(
        feedId: String? = null,
        archivingDestination: ArchivingDestinations?
    ) {
        when (archivingDestination) {
            ARCHIVING_MAIN -> archivingNavController.navigateToArchiveMain()
            ARCHIVING_DETAIL -> {
                if (feedId != null) {
                    archivingNavController.navigateToArchivingDetail(feedId)
                }
            }
            else -> {}
        }
    }

    fun navigateToMyArchiveDestination(
        screenIndex: Int? = null,
        myArchiveDestinations: MyArchiveDestinations?
    ) {
        when (myArchiveDestinations) {
            MY_ARCHIVE_MAIN -> myArchiveNavController.navigateToMyArchiveMain()
            MY_ARCHIVE_DETAIL -> {
                if (screenIndex != null) {
                    myArchiveNavController.navigateToMyArchiveDetail(screenIndex)
                }
            }
            else -> {}
        }
    }

    fun onNextProgress(navigate: () -> Unit) {
        // 완성 화면일 경우
        if (progressEnd) {
            return
        }

        // 다음 세부 단계가 남았을 경우
        with(currentProgressChildId) {
            if (this < MakeCarProcess.makeCarProcess[currentProgressId].children.last().id) {
                if (this < 0) navigate()
                currentProgressChildId += 1
                return
            }
        }

        currentProgressId.let { progressId ->
            if (progressId < MakeCarProcess.makeCarProcess.last().id) {
                currentProgressId = progressId + 1
                currentProgressChildId =
                    MakeCarProcess.makeCarProcess[progressId + 1].children.first().id
            } else { // 모든 단계가 끝나는 경우
                progressEnd = true
            }
        }
        navigate()
    }

    fun onBackProgress() {
        // 완성 화면일 경우
        if (progressEnd) {
            progressEnd = false
            makingCarNavController.popBackStack()
            return
        }

        // 이전 세부 단계가 남았을 경우
        val minChildId = if (MakeCarProcess.makeCarProcess[currentProgressId].needNoChild) -1 else 0
        with(currentProgressChildId) {
            if (this > minChildId) {
                currentProgressChildId -= 1
                if (this - 1 < 0)
                    makingCarNavController.popBackStack()
                return
            }
        }

        // 이전 단계가 남았을 경우
        currentProgressId.let { progressId ->
            if (progressId > MakeCarProcess.makeCarProcess.first().id) {
                currentProgressId = progressId - 1
                currentProgressChildId =
                    MakeCarProcess.makeCarProcess[progressId - 1].children.last().id
            } else { // 이전 단계가 없는 경우
                return
            }
        }
        makingCarNavController.popBackStack()
    }

}