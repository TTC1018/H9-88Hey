package com.softeer.mycarchiving.ui.archiving.archivingmain

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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private val TAG = ArchiveViewModel::class.simpleName

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArchiveViewModel @Inject constructor(
    getCarFeedsUseCase: GetCarFeedsUseCase,
    getAbleOptionsUseCase: GetAbleOptionsUseCase
) : ViewModel() {

    private val _showSearchSheet = MutableStateFlow(false)
    val showSearchSheet: StateFlow<Boolean> = _showSearchSheet

    private val _currentSheetPage = MutableStateFlow(SEARCH_CONDITION)
    val currentSheetPage: StateFlow<ArchiveSearchPage> = _currentSheetPage

    private val _ableCars = MutableStateFlow(SearchCarOptions.searchAbleCars)
    val ableCars: StateFlow<List<SearchOptionUiModel>> = _ableCars

    private val _selectedCar = MutableStateFlow(SearchOption(name = "팰리세이드", isSelect = true))
    val selectedCar: StateFlow<SearchOption> = _selectedCar

    private val _pendingCar = MutableStateFlow(_selectedCar.value)
    val pendingCar: StateFlow<SearchOption> = _pendingCar

    val ableOptions = getAbleOptionsUseCase()
        .map { it.asUiModel() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private val _appliedOptions = MutableStateFlow(emptyList<SearchOption>())
    val appliedOptions: StateFlow<List<SearchOption>> = _appliedOptions

    private val _selectedOptions = MutableStateFlow(emptyList<SearchOption>())
    val selectedOptions: StateFlow<List<SearchOption>> = _selectedOptions

    private val _pendingOptions = MutableStateFlow(emptyList<SearchOption>())
    val pendingOptions: StateFlow<List<SearchOption>> = _pendingOptions

    val carFeedPagingData = _appliedOptions.flatMapLatest { appliedOptions ->
        if (_showSearchSheet.value.not()) {
            getCarFeedsUseCase(appliedOptions.map { it.id })
        } else {
            flow {  }
        }
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