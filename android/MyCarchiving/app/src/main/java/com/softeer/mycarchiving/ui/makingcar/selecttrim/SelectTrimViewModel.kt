package com.softeer.mycarchiving.ui.makingcar.selecttrim

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.domain.model.TrimOption
import com.softeer.domain.usecase.makingcar.GetBodyTypesUseCase
import com.softeer.domain.usecase.makingcar.GetEngineOptionsUseCase
import com.softeer.domain.usecase.makingcar.GetWheelDrivesUseCase
import com.softeer.mycarchiving.mapper.asUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private val TAG = SelectTrimViewModel::class.simpleName

@HiltViewModel
class SelectTrimViewModel @Inject constructor(
    getEnginesUseCase: GetEngineOptionsUseCase,
    getBodyTypesUseCase: GetBodyTypesUseCase,
    getWheelDrivesUseCase: GetWheelDrivesUseCase,
) : ViewModel() {

    val engines = getEnginesUseCase()
        .map { it.map(TrimOption::asUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    val bodyTypes = getBodyTypesUseCase()
        .map { it.map(TrimOption::asUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    val wheels = getWheelDrivesUseCase()
        .map { it.map(TrimOption::asUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

}