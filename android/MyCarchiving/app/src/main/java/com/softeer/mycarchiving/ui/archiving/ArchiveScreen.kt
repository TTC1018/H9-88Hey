package com.softeer.mycarchiving.ui.archiving

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.ui.component.SearchCarBottomSheetContent
import com.softeer.mycarchiving.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveRoute(
    modifier: Modifier = Modifier,
    archiveViewModel: ArchiveViewModel = hiltViewModel(),
) {
    val showSearchSheet by archiveViewModel.showSearchSheet.collectAsStateWithLifecycle()
    val currentSheetPage by archiveViewModel.currentSheetPage.collectAsStateWithLifecycle()
    val selectedCarName by archiveViewModel.selectedCarName.collectAsStateWithLifecycle()
    val pendingCarName by archiveViewModel.pendingCarName.collectAsStateWithLifecycle()
    val selectedOptions by archiveViewModel.selectedOptions.collectAsStateWithLifecycle()
    val pendingOptions by archiveViewModel.pendingOptions.collectAsStateWithLifecycle()
    ArchiveScreen(
        modifier = modifier,
        openSearchSheet = archiveViewModel::openSearchSheet
    )
    if (showSearchSheet) {
        ModalBottomSheet(
            onDismissRequest = archiveViewModel::closeSearchSheet,
            containerColor = White,
            sheetState = SheetState(skipPartiallyExpanded = true),
            windowInsets = WindowInsets(top = 45.dp),
            scrimColor = Color.Transparent,
            dragHandle = null
        ) {
            SearchCarBottomSheetContent(
                currentPage = currentSheetPage,
                selectedCar = selectedCarName,
                pendingCar = pendingCarName,
                selectedOptions = selectedOptions,
                pendingOptions = pendingOptions,
                moveSetCar = archiveViewModel::moveSetCarSheet,
                moveSetOption = archiveViewModel::moveSetOptionSheet,
                onBackClick = archiveViewModel::onSheetBackClick,
                closeSheet = archiveViewModel::closeSearchSheet,
                onButtonClick = archiveViewModel::onSheetButtonClick
            )
        }
    }
}

@Composable
fun ArchiveScreen(
    modifier: Modifier,
    openSearchSheet: () -> Unit
) {
    Button(onClick = { openSearchSheet() }) {
        Text(text = "SearchFilter")
    }
}