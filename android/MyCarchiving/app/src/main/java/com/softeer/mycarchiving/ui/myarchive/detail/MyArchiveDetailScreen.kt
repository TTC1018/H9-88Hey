package com.softeer.mycarchiving.ui.myarchive.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import com.softeer.mycarchiving.ui.component.DetailBanner
import com.softeer.mycarchiving.ui.component.DetailReview
import com.softeer.mycarchiving.ui.component.DetailSelectedOption
import com.softeer.mycarchiving.ui.component.DetailTextLabel
import com.softeer.mycarchiving.ui.myarchive.main.MY_ARCHIVE_MADE
import com.softeer.mycarchiving.ui.myarchive.main.MY_ARCHIVE_SAVE
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun MyArchiveDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: MyArchiveDetailViewModel = hiltViewModel()
) {
    val previousScreenIndex by viewModel.screenIndex.collectAsStateWithLifecycle()
    val selectOptions by viewModel.selectOptions.collectAsStateWithLifecycle()

    MyArchiveDetailScreen(
        screenIndex = previousScreenIndex,
        selectOptions = selectOptions,
    )
}

@Composable
fun MyArchiveDetailScreen(
    modifier: Modifier = Modifier,
    screenIndex: Int,
    selectOptions: List<SelectOptionUiModel>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White)
                .padding(start = 16.dp, end = 16.dp, top = 27.dp)
        ) {
            DetailTextLabel(text = stringResource(id = R.string.archive_summary_car_info))
            DetailBanner()
            Spacer(modifier = Modifier.height(23.dp))
            if (screenIndex == MY_ARCHIVE_SAVE) {
                DetailTextLabel(text = stringResource(id = R.string.archive_general_review))
                Spacer(modifier = Modifier.height(16.dp))
                DetailReview(review = "")
                Spacer(modifier = Modifier.height(23.dp))
            }
            DetailTextLabel(text = stringResource(id = R.string.selected_option))
        }
        MyArchiveSelectOptionArea(
            selectOptions = selectOptions
        )
    }
}

@Composable
fun MyArchiveSelectOptionArea(
    modifier: Modifier = Modifier,
    selectOptions: List<SelectOptionUiModel>
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .background(color = HyundaiLightSand),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        selectOptions.forEachIndexed { idx, option ->
            DetailSelectedOption(
                optionImageUrl = option.imageUrl,
                optionNum = idx + 1,
                optionName = option.name,
                subOptions = option.subOptions?.map { it.name },

                )
        }
    }
}

@Preview
@Composable
fun PreviewMyArchiveDetailScreen() {
    MyArchiveDetailScreen(
        screenIndex = MY_ARCHIVE_MADE,
        selectOptions = listOf(
            SelectOptionUiModel(
                id = "",
                name = "컴포트 2",
                price = 1090000,
                imageUrl = "",
                subOptions = listOf(
                    SubSelectOptionUiModel(
                        name = "후석 승객 알림",
                        imageUrl = "",
                        description = "초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다."
                    ),
                    SubSelectOptionUiModel(
                        name = "메탈 리어범퍼스텝",
                        imageUrl = "",
                        description = "러기지 룸 앞쪽 하단부를 메탈로 만들어 물건을 싣고 내릴 때나 사람이 올라갈 때 차체를 보호해줍니다."
                    )
                ),
            ),
            SelectOptionUiModel(
                id = "",
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "",
                tags = listOf(
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
            )
        )
    )
}