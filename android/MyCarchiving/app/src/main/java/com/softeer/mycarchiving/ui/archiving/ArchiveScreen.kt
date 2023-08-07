package com.softeer.mycarchiving.ui.archiving

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ArchiveRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMyCarchiveClick: () -> Unit,
    archiveViewModel: ArchiveViewModel = hiltViewModel(),
) {
    ArchiveScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onMyCarchiveClick = onMyCarchiveClick,
    )
}

@Composable
fun ArchiveScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onMyCarchiveClick: () -> Unit,
) {
    Button(onClick = { onMyCarchiveClick() }) {
        Text(text = "Archive")
    }
}