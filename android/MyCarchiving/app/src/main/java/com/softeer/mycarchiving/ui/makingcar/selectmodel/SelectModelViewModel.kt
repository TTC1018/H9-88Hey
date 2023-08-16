package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.data.model.TrimModelDto
import com.softeer.data.repository.SelectTrimRepository
import com.softeer.mycarchiving.model.makingcar.ModelFeatureUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SelectModelViewModel @Inject constructor(
    private val selectTrimRepository: SelectTrimRepository
) : ViewModel() {

    val carModels = selectTrimRepository.getModels()
        .map { dtos -> dtos.map { it.asSelectModelUiModel() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val carImages = selectTrimRepository.getCarImageUrls()
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

    private fun TrimModelDto.asSelectModelUiModel(): SelectModelUiModel =
        SelectModelUiModel(
            name = name,
            price = price,
            features = modelFeatures.map { featureDto ->
                ModelFeatureUiModel(
                    name = featureDto.name,
                    imageUrl = featureDto.imageUrl
                )
            }
        )
}