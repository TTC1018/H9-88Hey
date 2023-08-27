package com.softeer.mycarchiving.ui.archiving.archivingmain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.softeer.domain.model.CarFeed
import com.softeer.domain.usecase.archiving.GetAbleOptionsUseCase
import com.softeer.domain.usecase.archiving.GetCarFeedsUseCase
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.archiving.SearchOptionUiModel
import com.softeer.mycarchiving.util.SearchCarOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArchiveViewModel @Inject constructor(
    getCarFeedsUseCase: GetCarFeedsUseCase,
    getAbleOptionsUseCase: GetAbleOptionsUseCase
) : ViewModel() {

    private val _showSearchSheet = mutableStateOf(false)
    val showSearchSheet: State<Boolean> = _showSearchSheet

    private val _currentSheetPage = mutableStateOf(SEARCH_CONDITION)
    val currentSheetPage: State<ArchiveSearchPage> = _currentSheetPage

    private val _ableCars = mutableStateOf(SearchCarOptions.searchAbleCars)
    val ableCars: State<List<SearchOptionUiModel>> = _ableCars

    private val _selectedCar = mutableStateOf(SearchOption(name = "팰리세이드", isSelect = true))
    val selectedCar: State<SearchOption> = _selectedCar

    private val _pendingCar = mutableStateOf(_selectedCar.value)
    val pendingCar: State<SearchOption> = _pendingCar

    val ableOptions = getAbleOptionsUseCase()
        .map { it.asUiModel() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )


    private val _appliedOptions = MutableStateFlow(emptyList<SearchOption>())
    val appliedOptions: StateFlow<List<SearchOption>> = _appliedOptions

    private val _selectedOptions = mutableStateOf(emptyList<SearchOption>())
    val selectedOptions: State<List<SearchOption>> = _selectedOptions

    private val _pendingOptions = mutableStateOf(emptyList<SearchOption>())
    val pendingOptions: State<List<SearchOption>> = _pendingOptions

    val carFeedPagingData = _appliedOptions.flatMapLatest { appliedOptions ->
        getCarFeedsUseCase(appliedOptions.map { it.id })
    }.map { pagingData -> pagingData.map(CarFeed::asUiModel) }
        .cachedIn(viewModelScope)


    fun openSearchSheet() {
        _selectedOptions.value = _appliedOptions.value
        _currentSheetPage.value = SEARCH_CONDITION
        _showSearchSheet.value = true
    }

    fun closeSearchSheet() {
        _showSearchSheet.value = false
    }

    fun onSheetBackClick() {
        _currentSheetPage.value = SEARCH_CONDITION
    }

    fun moveSetCarSheet() {
        _currentSheetPage.value = SET_CAR
    }

    fun moveSetOptionSheet() {
        initAbleOptions()
        _pendingOptions.value = _selectedOptions.value
        _currentSheetPage.value = SET_OPTION
    }

    fun onSheetButtonClick() {
        when (currentSheetPage.value) {
            SEARCH_CONDITION -> {
                _appliedOptions.value = _selectedOptions.value
                closeSearchSheet()
            }

            SET_CAR -> {
                onSheetBackClick()
            }

            SET_OPTION -> {
                _selectedOptions.value = _pendingOptions.value
                onSheetBackClick()
            }
        }
    }

    private fun initAbleOptions() {
        ableOptions.value.forEach { ableOption ->
            ableOption.options.forEach { option ->
                option.isSelect = option in _appliedOptions.value
            }
        }
    }

    fun onOptionChipClick(option: SearchOption) {
        if (_pendingOptions.value.contains(option)) {
            _pendingOptions.value = _pendingOptions.value.toMutableList().also {
                option.isSelect = false
                it.remove(option)
            }
        } else {
            _pendingOptions.value = _pendingOptions.value.toMutableList().also {
                option.isSelect = true
                it.add(option)
            }
        }
    }

    fun deleteAppliedOption(option: SearchOption) {
        _appliedOptions.value = _appliedOptions.value.toMutableList().also {
            option.isSelect = false
            it.remove(option)
        }
    }

    fun deleteSelectedOption(option: SearchOption) {
        _selectedOptions.value = _selectedOptions.value.toMutableList().also {
            option.isSelect = false
            it.remove(option)
        }
    }

    fun deletePendingOption(option: SearchOption) {
        _pendingOptions.value = _pendingOptions.value.toMutableList().also {
            option.isSelect = false
            it.remove(option)
        }
    }

}