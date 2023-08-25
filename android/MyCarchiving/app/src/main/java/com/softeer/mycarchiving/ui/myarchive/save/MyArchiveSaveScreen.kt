package com.softeer.mycarchiving.ui.myarchive.save

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.ui.component.MyArchiveLoadingScreen
import com.softeer.mycarchiving.ui.component.SavedCarFeed
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral

@Composable
fun MyArchiveSaveScreen(
    modifier: Modifier = Modifier,
    savedCars: LazyPagingItems<ArchiveFeedUiModel>,
    onFeedDetail: (ArchiveFeedUiModel) -> Unit,
    moveDetail: () -> Unit,
    openDeleteDialog: (ArchiveFeedUiModel) -> Unit,
) {
    when(savedCars.itemCount) {
        0 -> {
            MyArchiveLoadingScreen()
        }
        else -> {
            LazyColumn(
                modifier = modifier
                    .background(color = HyundaiNeutral)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                state = rememberLazyListState()
            ) {
                items(count = savedCars.itemCount) { index ->
                    savedCars[index]?.run {
                        SavedCarFeed(
                            date = this.date,
                            isPurchase = this.isSavedOrPurchase,
                            modelName = this.modelName,
                            trim = this.trimName!!,
                            trimOptions = this.trimOptions,
                            exteriorColor = this.exteriorColor!!.name,
                            interiorColor = this.interiorColor!!.name,
                            selectedOptions = this.selectedOptions.map { it.name },
                            review = this.review,
                            tags = this.tags,
                            onFeedClick = {
                                onFeedDetail(this)
                                moveDetail()
                            },
                            openDeleteDialog = { openDeleteDialog(this) }
                        )
                    }
                }
            }
        }
    }
}