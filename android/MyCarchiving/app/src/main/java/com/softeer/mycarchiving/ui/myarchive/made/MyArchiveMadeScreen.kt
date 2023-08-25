package com.softeer.mycarchiving.ui.myarchive.made

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.ui.component.MadeCarFeed
import com.softeer.mycarchiving.ui.component.MoveMakeCarDialog
import com.softeer.mycarchiving.ui.component.MyArchiveLoadingScreen
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand

@Composable
fun MyArchiveMadeScreen(
    modifier: Modifier = Modifier,
    showMoveDialog: Boolean,
    madeCars: LazyPagingItems<ArchiveFeedUiModel>,
    focusedCarFeed: ArchiveFeedUiModel?,
    onDetail: (ArchiveFeedUiModel) -> Unit,
    onClick: () -> Unit,
    openDeleteDialog: (ArchiveFeedUiModel) -> Unit,
    openMoveDialog: (ArchiveFeedUiModel) -> Unit,
    closeMoveDialog: () -> Unit
) {
    when (madeCars.itemCount) {
        0 -> {
            MyArchiveLoadingScreen()
        }
        else -> {
            LazyColumn(
                modifier = modifier
                    .background(HyundaiLightSand),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                state = rememberLazyListState()
            ) {
                items(count = madeCars.itemCount) { index ->
                    madeCars[index]?.run {
                        MadeCarFeed(
                            isTempSaved = this.isSavedOrPurchase.not(),
                            modelName = this.modelName,
                            trimName = this.trimName,
                            madeDate = this.date,
                            trimOptions = this.trimOptions.joinToString(" / "),
                            selectedOptions = this.selectedOptions,
                            onFeedClick = {
                                if (this.isSavedOrPurchase) {
                                    onDetail(this)
                                    onClick()
                                } else {
                                    openMoveDialog(this)
                                }
                            },
                            onDelete = { openDeleteDialog(this) }
                        )
                    }
                }
            }
        }
    }
    if (showMoveDialog) {
        MoveMakeCarDialog(onDismissRequest = closeMoveDialog, onMove = {}, saveDate = focusedCarFeed!!.date)
    }
}
