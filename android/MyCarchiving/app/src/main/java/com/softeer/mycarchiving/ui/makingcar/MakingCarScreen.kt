package com.softeer.mycarchiving.ui.makingcar

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MakingCarRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit,
    makingCarViewModel: MakingCarViewModel = hiltViewModel(),
) {
    MakingCarScreen(
        onBackClick = onBackClick,
        onArchiveClick = onArchiveClick,
    )
}

@Composable
internal fun MakingCarScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit,
) {
    Button(onClick = { onArchiveClick() }) {
        Text(text = "MakingCar")
    }
}