package com.softeer.mycarchiving.ui.loading

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun LoadingRoute(
    modifier: Modifier = Modifier,
    onLoading: () -> Unit,
    loadingViewModel: LoadingViewModel = hiltViewModel(),
) {
    LoadingScreen(
        modifier = modifier,
        onLoading = onLoading,
    )
}

@Composable
internal fun LoadingScreen(
    modifier: Modifier = Modifier,
    onLoading: () -> Unit,
) {
    Button(onClick = { onLoading() }) {
        Text(text = "Loading")
    }
}