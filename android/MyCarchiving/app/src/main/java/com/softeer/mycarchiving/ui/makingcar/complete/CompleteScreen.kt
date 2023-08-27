package com.softeer.mycarchiving.ui.makingcar.complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.domain.model.CarColorType
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import com.softeer.mycarchiving.ui.component.CompleteBanner
import com.softeer.mycarchiving.ui.component.CompleteCarCard
import com.softeer.mycarchiving.ui.component.CompleteCarTitleText
import com.softeer.mycarchiving.ui.component.OptionInfoDivider
import com.softeer.mycarchiving.ui.component.SelectedOptionInfo
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.ThinGray

@Composable
fun CompleteRoute(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner,
    makingCarViewModel: MakingCarViewModel = hiltViewModel(viewModelStoreOwner),
) {
    val carName by makingCarViewModel.selectedCarName.collectAsStateWithLifecycle()
    val carImage by makingCarViewModel.selectedCarImage.observeAsState()
    val selectedModelInfo by makingCarViewModel.selectedModelInfo.observeAsState()
    val totalPrice by makingCarViewModel.totalPrice.collectAsStateWithLifecycle()
    val colors by makingCarViewModel.selectedColor.collectAsStateWithLifecycle()
    val selectedTrims by makingCarViewModel.selectedTrim.collectAsStateWithLifecycle()
    val extraOptions by makingCarViewModel.totalExtraOptions.collectAsStateWithLifecycle()

    LaunchedEffect(extraOptions) {
        if (extraOptions.isNotEmpty()) {
            makingCarViewModel.saveCarInfo()
        }
    }

    CompleteScreen(
        modifier = modifier,
        carName = carName,
        carImage = carImage ?: "",
        modelName = selectedModelInfo?.name ?: stringResource(id = R.string.error_no_model),
        totalPrice = totalPrice,
        colors = colors,
        trimOptions = selectedTrims.map { it.optionName },
        selectedOptions = extraOptions
    )
}

@Composable
fun CompleteScreen(
    modifier: Modifier,
    carName: String,
    carImage: String,
    modelName: String,
    totalPrice: Int,
    colors: List<ColorOptionUiModel>,
    trimOptions: List<String>,
    selectedOptions: List<SelectOptionUiModel>,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        CompleteBanner(carName = carName, imageUrl = carImage)
        OptionInfoDivider(thickness = 4.dp, color = ThinGray)
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CompleteCarTitleText(
                text = stringResource(
                    id = R.string.make_car_done_feature,
                    carName
                )
            )
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
                    SelectedOptionInfo(optionInfo = option, thumbnailUrl = option.imageUrl)
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
            ColorOptionUiModel(
                id = 0,
                category = CarColorType.EXTERIOR,
                optionName = "문라이트 블루펄",
                imageUrl = "",
                price = 0,
                matchingColors = emptyList(),
                tags = emptyList()
            ),
            ColorOptionUiModel(
                id = 1,
                category = CarColorType.INTERIOR,
                optionName = "퀼팅 천연(블랙)",
                imageUrl = "",
                price = 0,
                matchingColors = emptyList(),
                tags = emptyList()
            )
        ),
        trimOptions = listOf(
            "디젤 2.2", "4WD", "7인승"
        ),
        selectedOptions = listOf(
            SelectOptionUiModel(
                id = "",
                name = "컴포트 II",
                price = 10900000,
                imageUrl = "",
                tags = listOf(
                    "어린이🧒",
                    "안전사고 예방🚨",
                    "대형견도 문제 없어요🐶",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
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
                )
            ),
            SelectOptionUiModel(
                id = "",
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "",
                tags = listOf(
                    "대형견도 문제 없어요🐶",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
                subOptions = emptyList()
            )
        )
    )
}