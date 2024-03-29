package com.softeer.mycarchiving.ui.makingcar.selectoption

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.domain.model.CarBasicOption
import com.softeer.domain.model.CarExtraOption
import com.softeer.domain.usecase.makingcar.GetBasicOptionsUseCase
import com.softeer.domain.usecase.makingcar.GetCarCodeUseCase
import com.softeer.domain.usecase.makingcar.GetExtraOptionsUseCase
import com.softeer.domain.usecase.makingcar.GetHGenuinesUseCase
import com.softeer.domain.usecase.makingcar.GetNPerformancesUseCase
import com.softeer.domain.usecase.makingcar.GetTagsOfSelectOptionUseCase
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_BODY
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_ENGINE
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_TRIM
import com.softeer.mycarchiving.ui.makingcar.KEY_SELECT_OPTION_WHEEL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private val TAG = SelectOptionViewModel::class.simpleName

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SelectOptionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCarCodeUseCase: GetCarCodeUseCase,
    getExtrasUseCase: GetExtraOptionsUseCase,
    getHGenuinesUseCase: GetHGenuinesUseCase,
    getNPerformancesUseCase: GetNPerformancesUseCase,
    getBasicUseCase: GetBasicOptionsUseCase,
    getTagsOfSelectOptionUseCase: GetTagsOfSelectOptionUseCase,
) : ViewModel() {

    private val _carCode = getCarCodeUseCase(
        trimId = savedStateHandle[KEY_SELECT_OPTION_TRIM] ?: 1,
        engineId = savedStateHandle[KEY_SELECT_OPTION_ENGINE] ?: 1,
        bodyTypeId = savedStateHandle[KEY_SELECT_OPTION_BODY] ?: 1,
        wheelId = savedStateHandle[KEY_SELECT_OPTION_WHEEL] ?: 1,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    val selectOptions = _carCode.flatMapLatest { carCode ->
        getExtrasUseCase(carCode)
    }
        .map { it.map(CarExtraOption::asUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val hGenuines = selectOptions.flatMapLatest { selectOptions ->
        if (_carCode.value.isNotEmpty() && selectOptions.isNotEmpty()) {
            getHGenuinesUseCase(_carCode.value, selectOptions.map { it.id })
        } else {
            flowOf()
        }
    }
        .map { it.map(CarExtraOption::asUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val nPerformances = _carCode.flatMapLatest { carCode ->
        getNPerformancesUseCase(carCode)
    }
        .map { it.map(CarExtraOption::asUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val selectOptionTagMap = selectOptions.flatMapLatest { options ->
        flowOf(
            buildMap {
                options.forEach { option ->
                    getTagsOfSelectOptionUseCase(option.id).firstOrNull()?.run {
                        put(option.id, this)
                    }
                }
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyMap()
    )

    val hGeniuneTagMap = hGenuines.flatMapLatest { options ->
        flowOf(
            buildMap {
                options.forEach { option ->
                    getTagsOfSelectOptionUseCase(option.id).firstOrNull()?.run {
                        put(option.id, this)
                    }
                }
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyMap()
    )

    val nPerformanceTagMap = nPerformances.flatMapLatest { options ->
        flowOf(
            buildMap {
                options.forEach { option ->
                    getTagsOfSelectOptionUseCase(option.id).firstOrNull()?.run {
                        put(option.id, this)
                    }
                }
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyMap()
    )

    private val _focusedOptionIndex = MutableStateFlow(0)
    val focusedOptionIndex: StateFlow<Int> = _focusedOptionIndex

    val basicOptions = _carCode.flatMapLatest { carCode ->
        getBasicUseCase(carCode)
    }
        .map { it.map(CarBasicOption::asUiModel) }
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
}