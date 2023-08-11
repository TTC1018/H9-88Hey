package com.softeer.mycarchiving.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.navigation.HyundaiNavHost
import com.softeer.mycarchiving.navigation.MainDestination.*
import com.softeer.mycarchiving.navigation.MakingCarDestinations.*
import com.softeer.mycarchiving.ui.component.ArchiveBottomBar
import com.softeer.mycarchiving.ui.component.ArchiveNavigateBar
import com.softeer.mycarchiving.ui.component.MakeCarBottomBar
import com.softeer.mycarchiving.ui.component.MakeCarNavigateBar
import com.softeer.mycarchiving.ui.component.MyArchiveBottomBar
import com.softeer.mycarchiving.ui.component.MyArchiveNavigateBar
import com.softeer.mycarchiving.ui.component.ProgressBar
import com.softeer.mycarchiving.ui.component.ReviewNavigateBar
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.makingcar.complete.navigateToComplete
import com.softeer.mycarchiving.ui.makingcar.selectcolor.navigateToSelectColor
import com.softeer.mycarchiving.ui.makingcar.selectoption.navigateToSelectOption
import com.softeer.mycarchiving.ui.makingcar.selecttrim.navigateToSelectTrim
import com.softeer.mycarchiving.util.MakeCarProcess.makeCarProcess

@Composable
fun HyundaiApp(
    appState: HyundaiAppState = rememberHyundaiAppState(),
    makingCarViewModel: MakingCarViewModel = hiltViewModel(),
) {
    val destination = appState.currentMainDestination
    val currentMakingCarProgress = appState.currentMakingCarDestinations

    val selectedCarModel by makingCarViewModel.selectedCarModel.collectAsStateWithLifecycle()
    val totalPrice by makingCarViewModel.totalPrice.collectAsStateWithLifecycle()

    val currentProgressId by makingCarViewModel.currentProgressId.collectAsStateWithLifecycle()
    val currentProgressChildId by makingCarViewModel.currentProgressChildId.collectAsStateWithLifecycle()
    val processEnd by makingCarViewModel.progressEnd.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier,
        topBar = {
            Log.d("HyundaiCompose", appState.currentDestination?.route ?: "null")
            when(destination) {
                ARCHIVING -> ArchiveNavigateBar(
                    onStartAreaClick = appState.navController::popBackStack,
                    onEndAreaClick = { appState.navigateToMainDestination(MY_ARCHIVING) }
                )

                MY_ARCHIVING -> MyArchiveNavigateBar(
                    onStartAreaClick = appState.navController::popBackStack
                )

                MAKING_CAR -> {
                    Column {
                        MakeCarNavigateBar(
                            carName = selectedCarModel,
                            onStartAreaClick = {
                               makingCarViewModel.onBackProgress {
                                   appState.navController.popBackStack()
                               }
                            },
                            onEndAreaClick = { appState.navigateToMainDestination(ARCHIVING) },
                            isDone = processEnd
                        )
                        ProgressBar(
                            makeCarProcess = makeCarProcess,
                            currentProgressId = currentProgressId,
                            currentChildId = currentProgressChildId,
                            isDone = processEnd
                        )
                    }
                }

                DRIVER_COMMENT,CONSUMER_COMMENT -> ReviewNavigateBar(
                    onStartAreaClick = appState.navController::popBackStack,
                    onEndAreaClick = { /*앱 종료*/ },
                    isBuyer = destination == CONSUMER_COMMENT
                )

                else -> @Composable {}
            }
        },
        bottomBar = {
            when(destination) {
                ARCHIVING -> ArchiveBottomBar(
                    totalPrice = 0,
                    onSaveClick = {},
                    onButtonClick = {}
                )

                MY_ARCHIVING -> MyArchiveBottomBar(
                    totalPrice = 0,
                    onButtonClick = {}
                )

                MAKING_CAR -> MakeCarBottomBar(
                    totalPrice = totalPrice,
                    onButtonClick = {
                        makingCarViewModel.onNextProgress {
                            when(currentMakingCarProgress) {
                                SELECT_MODEL -> appState.navController.navigateToSelectTrim()
                                SELECT_TRIM -> appState.navController.navigateToSelectColor()
                                SELECT_COLOR -> appState.navController.navigateToSelectOption()
                                SELECT_OPTION -> appState.navController.navigateToComplete()
                                else -> {}
                            }
                        }
                    },
                    onSummaryClick = makingCarViewModel::openSummary,
                    isDone = processEnd
                )

                DRIVER_COMMENT,CONSUMER_COMMENT -> {}
                else -> @Composable {}
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HyundaiNavHost(appState = appState)
        }
    }

}