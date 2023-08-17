package com.softeer.mycarchiving.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.softeer.mycarchiving.navigation.HyundaiNavHost
import com.softeer.mycarchiving.ui.component.HyundaiBottomBar
import com.softeer.mycarchiving.ui.component.HyundaiTopBar

@Composable
fun HyundaiApp(
    appState: HyundaiAppState = rememberHyundaiAppState(),
) {
    HyundaiNavHost(
        modifier = Modifier.fillMaxSize(),
        appState = appState
    )
}