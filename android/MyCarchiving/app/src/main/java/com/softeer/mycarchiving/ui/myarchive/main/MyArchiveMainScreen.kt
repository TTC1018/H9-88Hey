package com.softeer.mycarchiving.ui.myarchive.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel
import com.softeer.mycarchiving.ui.component.ChoiceTab
import com.softeer.mycarchiving.ui.myarchive.made.MyArchiveMadeScreen
import com.softeer.mycarchiving.ui.myarchive.save.MyArchiveSaveScreen

const val MY_ARCHIVE_MADE = 0
const val MY_ARCHIVE_SAVE = 1

@Composable
fun MyArchiveMainRoute(
    modifier: Modifier = Modifier,
    viewModel: MyArchiveMainViewModel = hiltViewModel(),
    onMadeCarClick: () -> Unit,
    onSavedCarClick: () -> Unit,
) {
    val selectedIndex by viewModel.selectedIndex.collectAsStateWithLifecycle()
    val madeCars by viewModel.madeCars.collectAsStateWithLifecycle()
    val savedCars by viewModel.savedCars.collectAsStateWithLifecycle()

    MyArchiveMainScreen(
        modifier = modifier,
        selectedIndex = selectedIndex,
        madeCars = madeCars,
        savedCars = savedCars,
        onSelect = viewModel::updateSelectedIndex,
        onMadeCarClick = onMadeCarClick,
        onMadeCarDelete = viewModel::deleteMadeCar,
        onSavedCarClick = onSavedCarClick,
    )
}

@Composable
fun MyArchiveMainScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    madeCars: List<MadeCarUiModel>,
    savedCars: List<CarFeedUiModel>,
    onSelect: (Int) -> Unit,
    onMadeCarClick: () -> Unit,
    onMadeCarDelete: (Int) -> Unit,
    onSavedCarClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChoiceTab(
                selectedIndex = selectedIndex,
                onSelect = onSelect
            )
        }

        AnimatedContent(
            targetState = selectedIndex,
            label = ""
        ) {
            when (it) {
                MY_ARCHIVE_MADE -> MyArchiveMadeScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    madeCars = madeCars,
                    onClick = onMadeCarClick,
                    onDelete = onMadeCarDelete,
                )

                MY_ARCHIVE_SAVE -> MyArchiveSaveScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    carFeeds = savedCars,
                    onClick = onSavedCarClick,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyArchiveMainScreen() {
    MyArchiveMainScreen(
        selectedIndex = 0,
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
        savedCars = listOf(
            CarFeedUiModel(
                id = 0,
                model = "팰리세이드",
                isPurchase = false,
                creationDate = "2023-07-19",
                trim = "Le Blanc",
                trimOptions = listOf("디젤 2.2", "4WD", "7인승"),
                interiorColor = "문라이트 블루 펄",
                exteriorColor = "퀄팅 천연(블랙)",
                selectedOptions = listOf(),
                review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
                tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘", "대형견도 문제 없어요🐶")
            )
        ),
        onSelect = {},
        onMadeCarClick = {},
        onMadeCarDelete = {},
        onSavedCarClick = {}
    )
}