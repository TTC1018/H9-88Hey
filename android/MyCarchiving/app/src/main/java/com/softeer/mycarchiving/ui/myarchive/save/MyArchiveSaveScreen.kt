package com.softeer.mycarchiving.ui.myarchive.save

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.ui.component.MyArchiveLoadingScreen
import com.softeer.mycarchiving.ui.component.SavedFeed
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyArchiveSaveScreen(
    modifier: Modifier = Modifier,
    carFeeds: List<CarFeedUiModel>,
    onClick: () -> Unit,
) {
    AnimatedContent(
        targetState = carFeeds,
        label = ""
    ) {
        when {
            it.isEmpty() -> MyArchiveLoadingScreen()
            else -> {
                LazyColumn(
                    modifier = modifier
                        .background(color = HyundaiNeutral)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    itemsIndexed(items = carFeeds, key = { idx, item -> item.id }) { idx, item ->
                        SavedFeed(
                            modifier = Modifier
                                .animateItemPlacement(),
                            carFeedUiModel = item,
                            onFeedClick = onClick,
                            onDelete = {}
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyArchiveSaveScreen() {
    MyArchiveSaveScreen(
        carFeeds = listOf(
            CarFeedUiModel(
                id = "",
                model = "팰리세이드",
                isPurchase = false,
                creationDate = "2023-07-19",
                trim = "Le Blanc",
                trimOptions = listOf("디젤 2.2", "4WD", "7인승"),
                interiorColor = "문라이트 블루 펄",
                exteriorColor = "퀄팅 천연(블랙)",
                selectedOptions = listOf("컴포트 ||", "듀얼 와이드 선루프"),
                review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
                tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘", "대형견도 문제 없어요🐶")
            )
        ),
        onClick = {},
    )
}