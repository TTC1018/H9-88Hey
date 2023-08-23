package com.softeer.mycarchiving.ui.myarchive.made

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel
import com.softeer.mycarchiving.ui.component.MadeCarItem
import com.softeer.mycarchiving.ui.component.MyArchiveLoadingScreen
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand

@Composable
fun MyArchiveMadeScreen(
    modifier: Modifier = Modifier,
    madeCars: LazyPagingItems<MadeCarUiModel>,
    onClick: () -> Unit,
    onDelete: (Int) -> Unit
) {
    AnimatedContent(
        targetState = madeCars,
        label = ""
    ) { pagingItems ->
        when (pagingItems.itemCount) {
            0 -> {
                MyArchiveLoadingScreen()
            }
            else -> {
                LazyColumn(
                    modifier = modifier
                        .background(HyundaiLightSand),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    items(count = madeCars.itemCount) { index ->
                        madeCars[index]?.run {
                            MadeCarItem(
                                isTempSaved = this.isSaved.not(),
                                modelName = this.modelName,
                                trimName = this.trimName,
                                madeDate = this.lastModifiedDate,
                                trimOptions = this.trimOptions.filterNotNull().joinToString(" / "),
                                selectedOptions = this.selectedOptions,
                                onItemClick = onClick,
                                onDelete = { onDelete(index) }
                            )
                        }
                    }
                }
            }
        }
    }
}
