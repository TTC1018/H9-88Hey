package com.softeer.mycarchiving.ui.myarchive.save

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.MyArchivePage
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.navigation.MyArchiveDestinations
import com.softeer.mycarchiving.ui.component.MyArchiveLoadingScreen
import com.softeer.mycarchiving.ui.component.SavedCarFeed
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral

@Composable
fun MyArchiveSaveScreen(
    modifier: Modifier = Modifier,
    savedCars: LazyPagingItems<ArchiveFeedUiModel>,
    onFeedDetail: (ArchiveFeedUiModel) -> Unit,
    moveDetail: (MyArchivePage?, String, MyArchiveDestinations?) -> Unit,
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
                            trim = this.trim?.name ?: stringResource(id = R.string.error_no_model),
                            trimOptions = this.trimOptions.map { it.name },
                            exteriorColor = this.exteriorColor!!.name,
                            interiorColor = this.interiorColor!!.name,
                            selectedOptions = this.selectedOptions.map { it.name },
                            review = this.review,
                            tags = this.tags,
                            onFeedClick = {
                                onFeedDetail(this)
                                moveDetail(MyArchivePage.SAVED, id, MyArchiveDestinations.MY_ARCHIVE_DETAIL)
                            },
                            openDeleteDialog = { openDeleteDialog(this) }
                        )
                    }
                }
            }
        }
    }
}