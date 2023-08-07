package com.softeer.mycarchiving.ui.mycar

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MyCarRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit,
    myCarViewModel: MyCarViewModel = hiltViewModel()
) {
    MyCarScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onArchiveClick = onArchiveClick,
    )
}

@Composable
internal fun MyCarScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onArchiveClick: () -> Unit,
) {
    Button(onClick = { onArchiveClick() }) {
        Text(text = "MyCar")
    }
}