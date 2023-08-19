package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.domain.model.TrimModelOption
import com.softeer.domain.usecase.makingcar.GetCarModelImagesUseCase
import com.softeer.domain.usecase.makingcar.GetCarModelsUseCase
import com.softeer.mycarchiving.mapper.asSelectModelUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SelectModelViewModel @Inject constructor(
    getModelsUseCase: GetCarModelsUseCase,
    getImageUrlsUseCase: GetCarModelImagesUseCase,
) : ViewModel() {

    val carModels = getModelsUseCase()
        .map { it.map(TrimModelOption::asSelectModelUiModel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val carImages = getImageUrlsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private val _focusedImageIndex = MutableStateFlow(0)
    val focusedImageIndex: StateFlow<Int> = _focusedImageIndex

    fun onCarImageClick(index: Int) {
        _focusedImageIndex.value = index
    }
}