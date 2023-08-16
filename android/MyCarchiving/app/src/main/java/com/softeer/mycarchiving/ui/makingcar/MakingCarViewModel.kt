package com.softeer.mycarchiving.ui.makingcar

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private val TAG = MakingCarViewModel::class.simpleName

@HiltViewModel
class MakingCarViewModel @Inject constructor() : ViewModel() {

    private val _selectedCarName = MutableStateFlow("팰리세이드")
    val selectedCarName: StateFlow<String> = _selectedCarName

    private val _selectedModelInfo = MutableLiveData<SelectModelUiModel>()
    val selectedModelInfo: LiveData<SelectModelUiModel> = _selectedModelInfo

    private val _selectedCarImage = MutableLiveData<String>()
    val selectedCarImage: LiveData<String> = _selectedCarImage

    private val _selectedColor = MutableStateFlow<MutableList<ColorOptionUiModel>>(mutableListOf())
    val selectedColor: StateFlow<List<ColorOptionUiModel>> = _selectedColor

    private val _selectedTrim = MutableStateFlow<MutableList<TrimOptionUiModel>>(mutableListOf())
    val selectedTrim: StateFlow<List<TrimOptionUiModel>> = _selectedTrim

    private val _selectedExtraOptions = MutableLiveData<List<SelectOptionUiModel>>()
    val selectedExtraOptions: LiveData<List<SelectOptionUiModel>> = _selectedExtraOptions

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    private val _showSummary = MutableStateFlow(false)
    val showSummary: StateFlow<Boolean> = _showSummary

    fun openSummary() {
        _showSummary.value = true
    }

    fun closeSummary() {
        _showSummary.value = false
    }

    fun updateSelectedModelInfo(selectedModel: SelectModelUiModel) {
        _selectedModelInfo.value = selectedModel
        _totalPrice.value += selectedModel.price
    }

    fun updateCarImageUrl(imageUrl: String) {
        _selectedCarImage.value = "${imageUrl}001.png"
    }

    fun updateSelectedTrimOption(
        trimOptionUiModel: TrimOptionUiModel,
        progress: Int,
        initial: Boolean = false
    ) {
        if (_selectedTrim.value.getOrNull(progress) == null) { // 그대로 추가하면 될 때
            _selectedTrim.value.add(trimOptionUiModel)
        } else if (initial.not()) { // 이미 기록된 데이터가 있는데 변경하려 할 때
            _selectedTrim.value[progress] = trimOptionUiModel
        }
    }

    fun updateSelectedColorOption(
        colorOptionUiModel: ColorOptionUiModel?,
        progress: Int,
        initial: Boolean
    ) {
        if (colorOptionUiModel != null) {
            if (_selectedColor.value.getOrNull(progress) == null) {
                _selectedColor.value.add(colorOptionUiModel)
            } else if (initial.not()) {
                _selectedColor.value[progress] = colorOptionUiModel
            }
        }
    }
}