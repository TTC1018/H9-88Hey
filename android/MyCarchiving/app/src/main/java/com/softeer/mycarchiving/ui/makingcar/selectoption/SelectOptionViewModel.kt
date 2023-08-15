package com.softeer.mycarchiving.ui.makingcar.selectoption

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.data.model.TrimHGenuineDto
import com.softeer.data.model.TrimSelectOptionDto
import com.softeer.data.model.TrimSubOptionDto
import com.softeer.data.repository.SelectOptionRepository
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.model.common.CarBasicUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = SelectOptionViewModel::class.simpleName

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SelectOptionViewModel @Inject constructor(
    private val selectOptionRepository: SelectOptionRepository
) : ViewModel() {

    private val _carCode = selectOptionRepository.getCarCode()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ""
        )

    val selectOptions = _carCode.flatMapLatest { carCode ->
        selectOptionRepository.getSelectOptions(carCode)
    }
        .map { dtos -> dtos.map { it.asSelectOptionUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val hGenuines = selectOptions.flatMapLatest { selectOptions ->
        if (_carCode.value.isNotEmpty() && selectOptions.isNotEmpty()) {
            selectOptionRepository.getHGenuines(_carCode.value, selectOptions.map { it.id })
        } else {
            flowOf()
        }
    }
        .map { dtos -> dtos.map { it.selectOption.asSelectOptionUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val nPerformances = _carCode.flatMapLatest { carCode ->
        selectOptionRepository.getNPerformances(carCode)
    }
        .map { dtos -> dtos.map { it.asSelectOptionUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val focusedOptionIndex = MutableStateFlow(0)

    private val basicDetailItems = listOf(
        CarBasicDetailUiModel(
            name = "ISG 시스템",
            description = "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
        ),
        CarBasicDetailUiModel(
            name = "ISG 시스템",
            description = "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
        ),
        CarBasicDetailUiModel(
            name = "ISG 시스템",
            description = "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
        )
    )

    private val basicItem1 = CarBasicUiModel(category = "파워트레인 성능", detailItems = basicDetailItems)
    private val basicItem2 = CarBasicUiModel(category = "지능형 안전 기술", detailItems = basicDetailItems)
    private val basicItem3 = CarBasicUiModel(category = "안전", detailItems = basicDetailItems)
    private val basicItem4 = CarBasicUiModel(category = "성능", detailItems = basicDetailItems)

    private val _basicItems =
        MutableStateFlow(listOf(basicItem1, basicItem2, basicItem3, basicItem4))
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

    private fun TrimSelectOptionDto.asSelectOptionUiModel() =
        SelectOptionUiModel(
            id = id,
            name = name,
            price = price,
            imageUrl = imageUrl,
            tags = tags,
            subOptions = subOptions.map { it.asSubSelectOptionUiModel() }
        )

    private fun TrimSubOptionDto.asSubSelectOptionUiModel() =
        SubSelectOptionUiModel(
            name = name,
            imageUrl = imageUrl,
            description = description,
        )
}