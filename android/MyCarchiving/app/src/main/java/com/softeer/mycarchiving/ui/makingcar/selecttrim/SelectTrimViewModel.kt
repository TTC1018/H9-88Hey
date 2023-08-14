package com.softeer.mycarchiving.ui.makingcar.selecttrim

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimWheelDto
import com.softeer.data.repository.SelectTrimRepository
import com.softeer.mycarchiving.model.TrimOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
    TrimOptionUiModel(
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
        maximumOutput = maximumPower,
        maximumTorque = maximumTorque,
    )

private fun TrimBodyTypeDto.asOptionUiModel() =
    TrimOptionUiModel(
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
    )

private fun TrimWheelDto.asOptionUiModel() =
    TrimOptionUiModel(
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
    )