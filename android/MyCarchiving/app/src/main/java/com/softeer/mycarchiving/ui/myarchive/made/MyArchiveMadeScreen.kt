package com.softeer.mycarchiving.ui.myarchive.made

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel
import com.softeer.mycarchiving.ui.component.MadeCarItem
import com.softeer.mycarchiving.ui.component.MyArchiveLoadingScreen
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyArchiveMadeScreen(
    modifier: Modifier = Modifier,
    madeCars: List<MadeCarUiModel>,
    onClick: () -> Unit,
    onDelete: (Int) -> Unit
) {
    AnimatedContent(
        targetState = madeCars,
        label = ""
    ) {
        when {
            it.isEmpty() -> {
                MyArchiveLoadingScreen()
            }
            else -> {
                LazyColumn(
                    modifier = modifier
                        .background(HyundaiLightSand),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    itemsIndexed(madeCars, key = { _, item -> item.id }) { idx, item ->
                        MadeCarItem(
                            modifier = Modifier.animateItemPlacement(),
                            isTempSaved = item.isSaved.not(),
                            modelName = item.modelName,
                            trimName = item.trimName,
                            madeDate = item.lastModifiedDate,
                            options = item.trimOptions.joinToString(" / "),
                            imageInfos = item.selectedOptions,
                            onItemClick = onClick,
                            onDelete = { onDelete(idx) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyArchiveMadeScreen() {
    MyArchiveMadeScreen(
        madeCars = listOf(
            MadeCarUiModel(
                id = "123",
                modelName = "팰리세이드",
                trimName = "Le Blanc",
                isSaved = false,
                lastModifiedDate = "2023-07-19",
                trimOptions = listOf(
                    "디젤 2.2", "4WD", "7인승"
                ),
                selectedOptions = listOf(
                    MadeCarSelectedOptionUiModel("컴포트 II", ""),
                    MadeCarSelectedOptionUiModel("듀얼 와이드 선루프", ""),
                    MadeCarSelectedOptionUiModel("현대스마트센스 I", "")
                ),
            ),
            MadeCarUiModel(
                id = "123",
                modelName = "팰리세이드",
                trimName = "Le Blanc",
                isSaved = true,
                lastModifiedDate = "2023-07-18",
                trimOptions = listOf(
                    "디젤 2.2", "2WD", "8인승"
                ),
                selectedOptions = listOf(
                    MadeCarSelectedOptionUiModel("적외선 무릎워머", ""),
                    MadeCarSelectedOptionUiModel("빌트인 공기청정기", ""),
                    MadeCarSelectedOptionUiModel("사이드 스텝", "")
                ),
            )
        ),
        onClick = {},
        onDelete = {}
    )
}