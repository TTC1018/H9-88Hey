package com.softeer.mycarchiving.ui.makingcar.selectoption

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.data.model.TrimBasicOptionDto
import com.softeer.data.model.TrimBasicSubOptionDto
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
        .map { dtos -> dtos.map { it.asSelectOptionUiModel() } }
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

    private val _focusedOptionIndex = MutableStateFlow(0)
    val focusedOptionIndex: StateFlow<Int> = _focusedOptionIndex

    val basicOptions = _carCode.flatMapLatest { carCode ->
        selectOptionRepository.getBasicOptions(carCode)
    }
        .map { dtos -> dtos.map { it.asCarBasicUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private val _showBasicItems = MutableStateFlow(false)
    val showBasicItems: StateFlow<Boolean> = _showBasicItems

    fun focusOptionItem(idx: Int) {
        _focusedOptionIndex.value = idx
    }

    fun openBasicItems() {
        _showBasicItems.value = true
    }

    fun closeBasicItems() {
        _showBasicItems.value = false
    }

    private fun TrimSelectOptionDto.asSelectOptionUiModel() =
        SelectOptionUiModel(
            isAvailable = isAvailable,
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

    private fun TrimBasicOptionDto.asCarBasicUiModel() =
        CarBasicUiModel(
            category = category,
            detailItems = subOptions.map { it.asCarBasicDetailUiModel() }
        )

    private fun TrimBasicSubOptionDto.asCarBasicDetailUiModel() =
        CarBasicDetailUiModel(
            name = name,
            description = description,
            imageUrl = imageUrl
        )
}