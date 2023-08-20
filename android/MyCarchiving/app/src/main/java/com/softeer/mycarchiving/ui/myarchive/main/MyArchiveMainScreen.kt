package com.softeer.mycarchiving.ui.myarchive.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel
import com.softeer.mycarchiving.ui.component.ChoiceTab
import com.softeer.mycarchiving.ui.myarchive.made.MyArchiveMadeScreen

@Composable
fun MyArchiveMainRoute(
    modifier: Modifier = Modifier,
    viewModel: MyArchiveMainViewModel = hiltViewModel(),
    onMadeCarClick: () -> Unit,
    onSavedCarClick: () -> Unit,
) {
    val selectedIndex by viewModel.selectedIndex.collectAsStateWithLifecycle()
    val madeCars by viewModel.madeCars.collectAsStateWithLifecycle()

    MyArchiveMainScreen(
        modifier = modifier,
        selectedIndex = selectedIndex,
        madeCars = madeCars,
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
    onSelect: (Int) -> Unit,
    onMadeCarClick: () -> Unit,
    onMadeCarDelete: (Int) -> Unit,
    onSavedCarClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ChoiceTab(
            selectedIndex = selectedIndex,
            onSelect = onSelect
        )
        AnimatedContent(
            targetState = selectedIndex,
            label = ""
        ) {
            when (it) {
                0 -> MyArchiveMadeScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    madeCars = madeCars,
                    onClick = onMadeCarClick,
                    onDelete = onMadeCarDelete,
                )

                1 -> {}
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
        onSelect = {},
        onMadeCarClick = {},
        onMadeCarDelete = {},
        onSavedCarClick = {}
    )
}