package com.softeer.mycarchiving.ui.makingcar.selecttrim

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimWheelDto
import com.softeer.data.repository.SelectTrimRepository
import com.softeer.mycarchiving.model.OptionCardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = SelectTrimViewModel::class.simpleName

@HiltViewModel
class SelectTrimViewModel @Inject constructor(
    private val selectTrimRepository: SelectTrimRepository
) : ViewModel() {

    val engines = selectTrimRepository.getEngines()
        .map { dtos -> dtos.map { it.asOptionUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    val bodyTypes = selectTrimRepository.getBodyTypes()
        .map { dtos -> dtos.map { it.asOptionUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    val wheels = selectTrimRepository.getWheelDrives()
        .map { dtos -> dtos.map { it.asOptionUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )



}

private fun TrimEngineDto.asOptionUiModel() =
    OptionCardUiModel(
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
        maximumOutput = maximumPower,
        maximumTorque = maximumTorque,
    )

private fun TrimBodyTypeDto.asOptionUiModel() =
    OptionCardUiModel(
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
    )

private fun TrimWheelDto.asOptionUiModel() =
    OptionCardUiModel(
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
    )