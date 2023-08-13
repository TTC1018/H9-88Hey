package com.softeer.mycarchiving.ui.makingcar.selectcolor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.data.model.TrimExteriorCarColor
import com.softeer.data.model.TrimInteriorCarColor
import com.softeer.data.repository.SelectColorRepository
import com.softeer.data.CarColorType
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val IMAGE_COUNT = 60

private val TAG = SelectColorViewModel::class.simpleName

@HiltViewModel
class SelectColorViewModel @Inject constructor(
    private val selectColorRepository: SelectColorRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            selectColorRepository.getCarColors().collect {
                when (it.firstOrNull()) {
                    is TrimExteriorCarColor -> {
                        _exteriors.value = it.map { dto ->
                            _imageUrls.value = _imageUrls.value + listOf((dto as TrimExteriorCarColor).carImagePath)
                            dto.asColorOptionUiModel()
                        }
                    }
                    is TrimInteriorCarColor -> {
                        _interiors.value = it.map { dto -> (dto as TrimInteriorCarColor).asColorOptionUiModel() }
                    }
                    else -> {}
                }
            }
        }
    }

    private val _exteriors = MutableStateFlow<List<ColorOptionUiModel>>(emptyList())
    val exteriors: StateFlow<List<ColorOptionUiModel>> = _exteriors

    private val _interiors = MutableStateFlow<List<ColorOptionUiModel>>(emptyList())
    val interiors: StateFlow<List<ColorOptionUiModel>> = _interiors

    private val _imageUrls = MutableStateFlow<List<String>>(emptyList())
    val imageUrls: StateFlow<List<String>> = _imageUrls

    private val _topImageIndex = MutableStateFlow(0)
    val topImageIndex: StateFlow<Int> = _topImageIndex

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex: StateFlow<Int> = _selectedIndex

    private val _category = MutableStateFlow("외장 색상")
    val category: StateFlow<String> = _category

    fun changeTopImageIndex(beAdded: Boolean) {
        _topImageIndex.value =
            if (beAdded) {
                (_topImageIndex.value + 1).mod(IMAGE_COUNT)
            } else {
                (_topImageIndex.value - 1).mod(IMAGE_COUNT)
            }
        Log.d(TAG, _topImageIndex.value.toString())
    }

    fun changeSelectedColor(selected: Int) {
        _selectedIndex.value = selected
    }

    private fun TrimExteriorCarColor.asColorOptionUiModel() =
        ColorOptionUiModel(
            category = CarColorType.EXTERIOR,
            optionName = name,
            imageUrl = colorImageUrl,
            price = additionalPrice,
            matchingColors = subInteriors,
            tags = tags ?: emptyList()
        )

    private fun TrimInteriorCarColor.asColorOptionUiModel() =
        ColorOptionUiModel(
            category = CarColorType.INTERIOR,
            optionName = name,
            imageUrl = colorImageUrl,
            price = 0,
            matchingColors = emptyList(),
            tags = tags ?: emptyList(),
        )
}