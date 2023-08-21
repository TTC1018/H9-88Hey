package com.softeer.mycarchiving.ui.makingcar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import com.softeer.domain.usecase.makingcar.SaveCarInfoUseCase
import com.softeer.domain.usecase.makingcar.SaveTempCarInfoUseCase
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = MakingCarViewModel::class.simpleName

@HiltViewModel
class MakingCarViewModel @Inject constructor(
    private val saveTempCarInfoUseCase: SaveTempCarInfoUseCase,
    private val saveCarInfoUseCase: SaveCarInfoUseCase
) : ViewModel() {

    private val _selectedCarName = MutableStateFlow("팰리세이드")
    val selectedCarName: StateFlow<String> = _selectedCarName

    private val _selectedModelInfo = MutableLiveData<SelectModelUiModel>()
    val selectedModelInfo: LiveData<SelectModelUiModel> = _selectedModelInfo

    private val _selectedCarImage = MutableLiveData<String>()
    val selectedCarImage: LiveData<String> = _selectedCarImage

    private val _selectedColor = MutableStateFlow<List<ColorOptionUiModel>>(emptyList())
    val selectedColor: StateFlow<List<ColorOptionUiModel>> = _selectedColor

    private val _selectedTrim = MutableStateFlow<List<TrimOptionUiModel>>(emptyList())
    val selectedTrim: StateFlow<List<TrimOptionUiModel>> = _selectedTrim

    private val _selectedExtraOptions = MutableStateFlow<List<SelectOptionUiModel>>(emptyList())
    val selectedExtraOptions: StateFlow<List<SelectOptionUiModel>> = _selectedExtraOptions

    private val _selectedHGenuines = MutableStateFlow<List<SelectOptionUiModel>>(emptyList())
    val selectedHGenuines: StateFlow<List<SelectOptionUiModel>> = _selectedHGenuines

    private val _selectedNPerformance = MutableStateFlow<List<SelectOptionUiModel>>(emptyList())
    val selectedNPerformance: StateFlow<List<SelectOptionUiModel>> = _selectedNPerformance

    val totalExtraOptions = combine(
        flow = _selectedExtraOptions,
        flow2 = _selectedHGenuines,
        flow3 = _selectedNPerformance
    ) { extras, hGenuines, nPerformance ->
        extras + hGenuines + nPerformance
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    private val _showSummary = MutableStateFlow(false)
    val showSummary: StateFlow<Boolean> = _showSummary

    private val carInfoId = MutableLiveData<Long>()

    fun openSummary() {
        _showSummary.value = true
    }

    fun closeSummary() {
        _showSummary.value = false
    }

    fun updateSelectedModelInfo(selectedModel: SelectModelUiModel) {
        if (_selectedModelInfo.value == null) {
            _selectedModelInfo.value = selectedModel
            _totalPrice.value += selectedModel.price
        }
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
            _selectedTrim.value += listOf(trimOptionUiModel)
            _totalPrice.value += trimOptionUiModel.price
        } else if (initial.not()) { // 이미 기록된 데이터가 있는데 변경하려 할 때
            _totalPrice.value -= _selectedTrim.value.getOrNull(progress)?.price ?: 0
            _selectedTrim.value = _selectedTrim.value.run {
                toMutableList().apply { set(progress, trimOptionUiModel) }
            }
            _totalPrice.value += _selectedTrim.value.getOrNull(progress)?.price ?: 0
        }
    }

    fun updateSelectedColorOption(
        colorOptionUiModel: ColorOptionUiModel?,
        progress: Int,
        initial: Boolean
    ) {
        if (colorOptionUiModel != null) {
            if (_selectedColor.value.getOrNull(progress) == null) {
                _selectedColor.value += listOf(colorOptionUiModel)
                _totalPrice.value += colorOptionUiModel.price
            } else if (initial.not()) {
                _totalPrice.value -= _selectedColor.value.getOrNull(progress)?.price ?: 0
                _selectedColor.value = _selectedColor.value.run {
                    toMutableList().apply { set(progress, colorOptionUiModel) }
                }
                _totalPrice.value += _selectedColor.value.getOrNull(progress)?.price ?: 0
            }
        }
    }

    fun updateSelectedExtraOption(extraOption: SelectOptionUiModel, progress: Int) {
        when (progress) {
            0 -> {
                _selectedExtraOptions.value = _selectedExtraOptions.value.run {
                    if (find { it.id == extraOption.id } != null) {
                        _totalPrice.value -= extraOption.price
                        toMutableList().apply { remove(extraOption) }
                    } else {
                        _totalPrice.value += extraOption.price
                        this + listOf(extraOption)
                    }
                }
            }

            1 -> {
                _selectedHGenuines.value = _selectedHGenuines.value.run {
                    if (find { it.id == extraOption.id } != null) {
                        _totalPrice.value -= extraOption.price
                        toMutableList().apply { remove(extraOption) }
                    } else {
                        _totalPrice.value += extraOption.price
                        this + listOf(extraOption)
                    }
                }
            }

            2 -> { // NPerformance는 한개만
                _totalPrice.value -= _selectedNPerformance.value.firstOrNull()?.price ?: 0
                _selectedNPerformance.value = listOf(extraOption)
                _totalPrice.value += extraOption.price
            }
        }
    }

    fun saveTempCarInfo() {
        val carTempInfo = CarTempInfo(
            infoId = carInfoId.value,
            modelId = 1,
            engineId = _selectedTrim.value.getOrNull(0)?.id,
            bodyTypeId = _selectedTrim.value.getOrNull(1)?.id,
            wheelTypeId = _selectedTrim.value.getOrNull(2)?.id,
            exteriorColorId = _selectedColor.value.getOrNull(0)?.id,
            interiorColorId = _selectedColor.value.getOrNull(1)?.id,
            selectOptionsIds = totalExtraOptions.value.map { it.id }
        )

        viewModelScope.launch {
            carInfoId.value = saveTempCarInfoUseCase(carTempInfo).firstOrNull()
        }
    }

    fun saveCarInfo() {
        carInfoId.value?.let { infoId ->
            val carInfo = CarInfo(
                infoId = infoId,
                modelId = 1,
                engineId = _selectedTrim.value[0].id,
                bodyTypeId = _selectedTrim.value[1].id,
                wheelTypeId = _selectedTrim.value[2].id,
                exteriorColorId = _selectedColor.value[0].id,
                interiorColorId = _selectedColor.value[1].id,
                selectOptionsIds = totalExtraOptions.value.map { it.id }
            )

            viewModelScope.launch {
                carInfoId.value = saveCarInfoUseCase(carInfo).firstOrNull()
            }
        }
    }
}