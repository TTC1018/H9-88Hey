package com.softeer.mycarchiving.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.softeer.mycarchiving.navigation.HyundaiNavHost

@Composable
fun HyundaiApp(
    appState: HyundaiAppState = rememberHyundaiAppState(),
) {
    HyundaiNavHost(
        modifier = Modifier.fillMaxSize(),
        appState = appState
    )
}