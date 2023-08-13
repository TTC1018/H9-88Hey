package com.softeer.mycarchiving.ui.makingcar

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
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

    private val _selectedColor = MutableLiveData<List<ColorOptionUiModel>>()
    val selectedColor: LiveData<List<ColorOptionUiModel>> = _selectedColor

    private var selectedTrimState = mutableStateListOf<TrimOptionUiModel>()
    private val _selectedTrim = MutableStateFlow<List<TrimOptionUiModel>>(selectedTrimState)
    val selectedTrim: StateFlow<List<TrimOptionUiModel>> = _selectedTrim

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
    }

    fun updateSelectedTrimOption(trimOptionUiModel: TrimOptionUiModel) {
        selectedTrimState = mutableStateListOf<TrimOptionUiModel>().apply {
            addAll(_selectedTrim.value.dropLast(1))
            add(trimOptionUiModel)
        }
        _selectedTrim.value = selectedTrimState
        Log.d(TAG, _selectedTrim.value.joinToString(" "))
    }
}