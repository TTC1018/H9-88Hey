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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel
import com.softeer.mycarchiving.ui.component.DetailBanner
import com.softeer.mycarchiving.ui.component.DetailReview
import com.softeer.mycarchiving.ui.component.DetailSelectedOption
import com.softeer.mycarchiving.ui.component.DetailTextLabel
import com.softeer.mycarchiving.ui.component.MyArchiveDetailBottomBar
import com.softeer.mycarchiving.ui.myarchive.main.MY_ARCHIVE_SAVE
import com.softeer.mycarchiving.ui.myarchive.main.MyArchiveMainViewModel
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun MyArchiveDetailRoute(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner?,
    viewModel: MyArchiveMainViewModel =
        viewModelStoreOwner?.run { hiltViewModel(this) } ?: hiltViewModel()
) {
    val screenIndex by viewModel.selectedIndex
    val madeCarDetail by viewModel.detailCar

    MyArchiveDetailScreen(
        modifier = modifier,
        screenIndex = screenIndex,
        detailCar = madeCarDetail!!
    )
}

@Composable
fun MyArchiveDetailScreen(
    modifier: Modifier,
    screenIndex: Int,
    detailCar: MadeCarUiModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
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
                DetailBanner(
                    carImageUrl = detailCar.carImageUrl ?: "",
                    model = detailCar.modelName,
                    trim = detailCar.trimName ?: "",
                    trimOptions = detailCar.trimOptions.filterNotNull().joinToString(" / "),
                    price = detailCar.totalPrice,
                    exteriorColor = detailCar.exteriorColor?.name ?: "",
                    exteriorColorUrl = detailCar.exteriorColor?.colorImageUrl ?: "",
                    interiorColor = detailCar.interiorColor?.name ?: "",
                    interiorColorUrl = detailCar.interiorColor?.colorImageUrl ?: ""
                )
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
                selectOptions = detailCar.selectedOptions
            )
        }
        MyArchiveDetailBottomBar(screenIndex = screenIndex, totalPrice = detailCar.totalPrice)
    }
}

@Composable
fun MyArchiveSelectOptionArea(
    modifier: Modifier = Modifier,
    selectOptions: List<MadeCarSelectedOptionUiModel>
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
                subOptions = option.subOptions,
            )
        }
    }
}

/*
@Preview
@Composable
fun PreviewMyArchiveDetailScreen() {
    MyArchiveDetailScreen(
        screenIndex = MY_ARCHIVE_MADE,
        review = "리뷰 텍스트",
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
}*/
