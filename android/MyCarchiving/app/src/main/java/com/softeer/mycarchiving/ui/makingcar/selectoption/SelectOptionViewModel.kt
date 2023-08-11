package com.softeer.mycarchiving.ui.makingcar.selectoption

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.model.common.CarBasicUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SelectOptionViewModel @Inject constructor() : ViewModel() {

    private val subOptions = listOf(
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

    private val _options = MutableStateFlow(
        listOf(
            SelectOptionUiModel(
                name = "컴포트 2",
                price = 10900000,
                imageUrl = "",
                tags = listOf(
                    "어린이🧒",
                    "안전사고 예방🚨",
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
                subOptions = subOptions
            ),
            SelectOptionUiModel(
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "",
                tags = listOf(
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
                subOptions = subOptions
            )
        )
    )
    val options: StateFlow<List<SelectOptionUiModel>> = _options
    val focusedOptionIndex = MutableStateFlow(0)


    private val basicDetailItems = listOf(
        CarBasicDetailUiModel(name = "ISG 시스템", description = "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
        CarBasicDetailUiModel(name = "ISG 시스템", description = "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
        CarBasicDetailUiModel(name = "ISG 시스템", description = "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.")
    )

    private val basicItem1 = CarBasicUiModel(category = "파워트레인 성능", detailItems = basicDetailItems)
    private val basicItem2 = CarBasicUiModel(category = "지능형 안전 기술", detailItems = basicDetailItems)
    private val basicItem3 = CarBasicUiModel(category = "안전", detailItems = basicDetailItems)
    private val basicItem4 = CarBasicUiModel(category = "성능", detailItems = basicDetailItems)

    private val _basicItems = MutableStateFlow(listOf(basicItem1, basicItem2, basicItem3, basicItem4))
    val basicItems: StateFlow<List<CarBasicUiModel>> = _basicItems
    private val _showBasicItems = MutableStateFlow(false)
    val showBasicItems: StateFlow<Boolean> = _showBasicItems

    fun focusOptionItem(idx: Int) {
        focusedOptionIndex.value = idx
    }

    fun openBasicItems() {
        _showBasicItems.value = true
    }

    fun closeBasicItems() {
        _showBasicItems.value = false
    }

    fun onAddOption() {

    }
}