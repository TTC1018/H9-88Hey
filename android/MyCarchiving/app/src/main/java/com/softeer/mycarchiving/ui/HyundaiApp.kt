package com.softeer.mycarchiving.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.navigation.HyundaiNavHost
import com.softeer.mycarchiving.navigation.MainDestination.*
import com.softeer.mycarchiving.ui.component.ArchiveBottomBar
import com.softeer.mycarchiving.ui.component.ArchiveNavigateBar
import com.softeer.mycarchiving.ui.component.MakeCarBottomBar
import com.softeer.mycarchiving.ui.component.MakeCarNavigateBar
import com.softeer.mycarchiving.ui.component.MyArchiveBottomBar
import com.softeer.mycarchiving.ui.component.MyArchiveNavigateBar
import com.softeer.mycarchiving.ui.component.ReviewNavigateBar
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel

@Composable
fun HyundaiApp(
    appState: HyundaiAppState = rememberHyundaiAppState(),
    makingCarViewModel: MakingCarViewModel = hiltViewModel(),
) {
    val destination = appState.currentMainDestination
    val selectedCarModel = makingCarViewModel.selectedCarModel.collectAsStateWithLifecycle().value
    val totalPrice = makingCarViewModel.totalPrice.collectAsStateWithLifecycle().value
    val processDone = makingCarViewModel.progressEnd.collectAsStateWithLifecycle().value
    Scaffold(
        modifier = Modifier,
        topBar = {
            Log.d("HyundaiCompose", appState.currentDestination?.route ?: "null")
            val topBarColor = appState.currentTopBarColor
            when(destination) {
                ARCHIVING -> ArchiveNavigateBar(
                    onStartAreaClick = {},
                    onEndAreaClick = {
                        appState.navigateToMainDestination(MY_ARCHIVING)
                    }
                )

                MY_ARCHIVING -> MyArchiveNavigateBar(
                    onStartAreaClick = {}
                )

                MAKING_CAR -> MakeCarNavigateBar(
                    carName = selectedCarModel,
                    onStartAreaClick = {},
                    onEndAreaClick = {
                        appState.navigateToMainDestination(ARCHIVING)
                    },
                    isDone = processDone
                )

                DRIVER_COMMENT -> ReviewNavigateBar(
                    onStartAreaClick = {},
                    onEndAreaClick = {},
                    isBuyer = false
                )

                CONSUMER_COMMENT -> ReviewNavigateBar(
                    onStartAreaClick = {},
                    onEndAreaClick = {},
                    isBuyer = true
                )

                else -> @Composable {}
            }
 /*           if (destination != null) {
                HyundaiTopBar(
                    titleRes = destination.titleTextId,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = topBarColor
                    ),
                    onNaviButtonClick = {},
                    onActionClick = {},
                )
            }*/
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
                    onButtonClick = makingCarViewModel::onNextProgress,
                    onSummaryClick = makingCarViewModel::openSummary,
                    isDone = processDone
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