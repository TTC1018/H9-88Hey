package com.softeer.mycarchiving.ui.myarchive

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MyArchivingRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    myArchivingViewModel: MyArchivingViewModel = hiltViewModel()
) {
    MyArchivingScreen(
        modifier = modifier,
        onBackClick = onBackClick
    )
}

@Composable
internal fun MyArchivingScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Button(onClick = { onBackClick() }) {
        Text(text = "MyCar")
    }
}