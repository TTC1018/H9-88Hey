package com.softeer.mycarchiving.ui.archiving.archivingmain

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.navigation.ArchivingDestinations
import com.softeer.mycarchiving.navigation.MyArchiveDestinations
import com.softeer.mycarchiving.ui.component.ArchiveFeed
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
    moveDetailPage: (String, ArchivingDestinations?) -> Unit,
) {
    val showSearchSheet by archiveViewModel.showSearchSheet.collectAsStateWithLifecycle()
    val currentSheetPage by archiveViewModel.currentSheetPage.collectAsStateWithLifecycle()
    val ableCars by archiveViewModel.ableCars.collectAsStateWithLifecycle()
    val selectedCar by archiveViewModel.selectedCar.collectAsStateWithLifecycle()
    val pendingCar by archiveViewModel.pendingCar.collectAsStateWithLifecycle()
    val appliedOptions by archiveViewModel.appliedOptions.collectAsStateWithLifecycle()
    val selectedOptions by archiveViewModel.selectedOptions.collectAsStateWithLifecycle()
    val pendingOptions by archiveViewModel.pendingOptions.collectAsStateWithLifecycle()
    val ableOptions by archiveViewModel.ableOptions.collectAsStateWithLifecycle()
    val carFeeds = archiveViewModel.carFeedPagingData.collectAsLazyPagingItems()

    ArchiveScreen(
        modifier = modifier,
        selectedCar = selectedCar,
        appliedOptions = appliedOptions,
        carFeeds = carFeeds,
        deleteSelectedChip = archiveViewModel::deleteAppliedOption,
        openSearchSheet = archiveViewModel::openSearchSheet,
        onFeedClick = moveDetailPage
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
                selectedCar = selectedCar,
                pendingCar = pendingCar,
                ableCars = ableCars,
                selectedOptions = selectedOptions,
                pendingOptions = pendingOptions,
                ableOptions = ableOptions,
                ableOptionsSize = ableOptions.sumOf { it.options.size },
                onOptionChipClick = archiveViewModel::onOptionChipClick,
                deleteSelectedOptionChip = archiveViewModel::deleteSelectedOption,
                deletePendingOptionChip = archiveViewModel::deletePendingOption,
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
    selectedCar: SearchOption,
    appliedOptions: List<SearchOption>,
    carFeeds: LazyPagingItems<CarFeedUiModel>,
    deleteSelectedChip: (SearchOption) -> Unit,
    openSearchSheet: () -> Unit,
    onFeedClick: (String, ArchivingDestinations?) -> Unit,
) {
    val scrollState = rememberLazyListState()
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
                    text = selectedCar.name,
                    style = medium18,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null
                )
            }
            AnimatedVisibility(visible = !scrollState.canScrollBackward && appliedOptions.isNotEmpty()) {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.archive_get_selected_option),
                        style = medium14,
                        color = DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SearchDeleteChipFlowList(
                        options = appliedOptions,
                        horizontalSpace = 4,
                        deleteChip = deleteSelectedChip
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = HyundaiLightSand)
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.archive_get_recent_review, selectedCar.name),
                style = medium14,
                color = DarkGray
            )
            LazyColumn(
                contentPadding = PaddingValues(bottom = 20.dp),
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(count = carFeeds.itemCount) { index ->
                    carFeeds[index]?.run {
                        ArchiveFeed(
                            carFeedUiModel = this,
                            appliedOptions = appliedOptions,
                            onFeedClick = onFeedClick
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewArchiveRoute() {
    ArchiveRoute(moveDetailPage = { _, _ -> })
}