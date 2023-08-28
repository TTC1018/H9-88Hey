package com.softeer.mycarchiving.ui.makingcar

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
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
import com.softeer.mycarchiving.ui.myarchive.KEY_MYARCHIVE_FEED_DATA
import com.softeer.mycarchiving.ui.myarchive.TempCarType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun NavController.navigateToMakingCar(
    feedId: String? = null,
    tempCar: ArchiveFeedUiModel? = null,
    navOptions: NavOptions? = null
) {
    when {
        feedId != null -> navigate(
            "${MainDestination.MAKING_CAR.route}?$KEY_ARCHIVE_FEED_ID=$feedId&$KEY_MYARCHIVE_FEED_DATA=$tempCar",
            navOptions
        )
        tempCar != null -> {
            val tempCarUri = Uri.encode(Json.encodeToString(tempCar))
            navigate(
                "${MainDestination.MAKING_CAR.route}?$KEY_ARCHIVE_FEED_ID=$feedId&$KEY_MYARCHIVE_FEED_DATA=$tempCarUri",
                navOptions
            )
        }
        else -> navigate(MainDestination.MAKING_CAR.route, navOptions)
    }
}

fun NavGraphBuilder.makingCarGraph(
    appState: HyundaiAppState,
) {
    composable(
        route = "${MainDestination.MAKING_CAR.route}?$KEY_ARCHIVE_FEED_ID={$KEY_ARCHIVE_FEED_ID}&$KEY_MYARCHIVE_FEED_DATA={$KEY_MYARCHIVE_FEED_DATA}",
        arguments = listOf(
            navArgument(KEY_ARCHIVE_FEED_ID) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(KEY_MYARCHIVE_FEED_DATA) {
                type = TempCarType()
                nullable = true
            }
        ),
    ) {
        appState.makingCarNavController = rememberNavController()
        val mainProgress = appState.currentProgressId
        val screenProgress = appState.currentProgressChildId
        val makingCarViewModelOwner: ViewModelStoreOwner = remember(it) {
            appState.navController.getBackStackEntry(MainDestination.MAKING_CAR.route)
        }
        val activity = LocalContext.current as Activity

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
                        onBackProgress = activity::finish
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