package com.softeer.mycarchiving.ui.makingcar.complete

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.softeer.mycarchiving.model.makingcar.ColorOptionSimpleUiModel
import com.softeer.mycarchiving.model.makingcar.CompleteOptionUiModel
import com.softeer.mycarchiving.ui.component.CompleteBanner
import com.softeer.mycarchiving.ui.component.CompleteCarCard
import com.softeer.mycarchiving.ui.component.CompleteCarTitleText
import com.softeer.mycarchiving.ui.component.OptionInfoDivider
import com.softeer.mycarchiving.ui.component.SelectedOptionInfo
import com.softeer.mycarchiving.ui.theme.LightGray

@Composable
fun CompleteRoute(
    modifier: Modifier = Modifier,
    completeViewModel: CompleteViewModel = hiltViewModel()
) {
    val carName by completeViewModel.carName.collectAsStateWithLifecycle()
    val carImage by completeViewModel.carImage.collectAsStateWithLifecycle()
    val modelName by completeViewModel.modelName.collectAsStateWithLifecycle()
    val totalPrice by completeViewModel.totalPrice.collectAsStateWithLifecycle()
    val colors by completeViewModel.colors.collectAsStateWithLifecycle()
    val trimOptions by completeViewModel.trimOptions.collectAsStateWithLifecycle()
    val selectedOptions by completeViewModel.selectedOptions.collectAsStateWithLifecycle()


    CompleteScreen(
        modifier = modifier,
        carName = carName,
        carImage = carImage,
        modelName = modelName,
        totalPrice = totalPrice,
        colors = colors,
        trimOptions = trimOptions,
        selectedOptions = selectedOptions
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompleteScreen(
    modifier: Modifier,
    carName: String,
    carImage: String,
    modelName: String,
    totalPrice: Int,
    colors: List<ColorOptionSimpleUiModel>,
    trimOptions: List<String>,
    selectedOptions: List<CompleteOptionUiModel>,
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CompleteBanner(carName = carName, imageUrl = carImage)
        OptionInfoDivider(thickness = 4.dp, color = LightGray)
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CompleteCarTitleText(text = stringResource(id = R.string.make_car_done_feature, carName))
            CompleteCarCard(
                carName = carName,
                modelName = modelName,
                options = trimOptions,
                colors = colors,
                price = totalPrice
            )
            CompleteCarTitleText(
                text = stringResource(
                    id = R.string.make_car_selected_option,
                    selectedOptions.size
                )
            )
            Column {
                OptionInfoDivider(thickness = 1.dp, color = LightGray)
                selectedOptions.forEach { option ->
                    SelectedOptionInfo(optionInfo = option, thumbnailUrl = option.thumbnailUrl)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCompleteScreen() {
    CompleteScreen(
        modifier = Modifier,
        carName = "팰리세이드",
        carImage = "",
        modelName = "Le Blanc(르블랑)",
        totalPrice = 47340000,
        colors = listOf(
            ColorOptionSimpleUiModel(category = "외장", "", "문라이트 블루펄"),
            ColorOptionSimpleUiModel(category = "내장", "", "퀼팅 천연(블랙)")
        ),
        trimOptions = listOf(
            "디젤 2.2", "4WD", "7인승"
        ),
        selectedOptions = listOf(
            CompleteOptionUiModel(
                optionName = "컴포트 II",
                price = 1090000,
                subOptionNames = listOf(
                    "후석 승객 알림", "메탈 리어 범퍼스텝",
                    "메탈 도어스커프", "3열 파워폴딩시트",
                    "3열 열선시트", "헤드업 디스플레이"
                )
            ),
            CompleteOptionUiModel(
                optionName = "컴포트 II",
                price = 1090000,
                subOptionNames = listOf(
                    "후석 승객 알림", "메탈 리어 범퍼스텝",
                    "메탈 도어스커프", "3열 파워폴딩시트",
                    "3열 열선시트", "헤드업 디스플레이"
                )
            )
        )
    )
}