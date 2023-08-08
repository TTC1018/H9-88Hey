package com.softeer.mycarchiving.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.softeer.mycarchiving.navigation.HyundaiNavHost
import com.softeer.mycarchiving.ui.component.HyundaiTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HyundaiApp(
    appState: HyundaiAppState = rememberHyundaiAppState()
) {

    Scaffold(
        modifier = Modifier,
        topBar = {
            val destination = appState.currentMainDestination
            val topBarColor = appState.currentTopBarColor
            if (destination != null) {
                HyundaiTopBar(
                    titleRes = destination.titleTextId,
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = topBarColor
                    ),
                    onNaviButtonClick = {},
                    onActionClick = {},
                )
            }
        },
        bottomBar = {}
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