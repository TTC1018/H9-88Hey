package com.softeer.mycarchiving.ui.archiving

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.component.SearchCarBottomSheetContent
import com.softeer.mycarchiving.ui.component.SearchDeleteChipFlowList
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.roundCorner

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
        selectedCar = selectedCarName,
        selectedOptions = selectedOptions,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArchiveScreen(
    modifier: Modifier,
    selectedCar: String,
    selectedOptions: List<String>,
    openSearchSheet: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = HyundaiLightSand, shape = roundCorner)
                    .clickable { openSearchSheet() }
                    .padding(start = 16.dp, end = 7.dp, top = 9.dp, bottom = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = selectedCar,
                    style = medium18,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.archive_get_selected_option),
                style = medium14,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            SearchDeleteChipFlowList(
                options = selectedOptions,
                horizontalSpace = 4
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = HyundaiLightSand)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            stickyHeader {
                Text(
                    text = stringResource(id = R.string.archive_get_recent_review, selectedCar),
                    style = medium14,
                    color = DarkGray
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewArchiveRoute() {
    ArchiveRoute()
}