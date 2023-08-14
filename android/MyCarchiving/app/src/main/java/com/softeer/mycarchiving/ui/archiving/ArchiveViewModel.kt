package com.softeer.mycarchiving.ui.archiving

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(): ViewModel() {

    private val _showSearchSheet = MutableStateFlow(false)
    val showSearchSheet: StateFlow<Boolean> = _showSearchSheet

    private val _currentSheetPage = MutableStateFlow(SEARCH_CONDITION)
    val currentSheetPage: StateFlow<ArchiveSearchPage> = _currentSheetPage

    private val _selectedCarName = MutableStateFlow("펠리세이드")
    val selectedCarName: StateFlow<String> = _selectedCarName
    val pendingCarName = MutableStateFlow(_selectedCarName.value)
    val selectedOptions = MutableStateFlow(mutableListOf("컴포트 2 패키지", "듀얼 와이드 선루프", "컴포트 2 패키지", "듀얼 와이드 선루프"))
    val pendingOptions =  MutableStateFlow(mutableListOf("컴포트 2 패키지", "듀얼 와이드 선루프", "컴포트 2 패키지", "듀얼 와이드 선루프"))

    fun openSearchSheet() {
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
        _currentSheetPage.value = SET_OPTION
    }

    fun onSheetButtonClick() {
        when(currentSheetPage.value) {
            SEARCH_CONDITION -> {
                /*검색 API에 적용*/
                closeSearchSheet()
            }
            SET_CAR -> {
                _selectedCarName.value = pendingCarName.value
                onSheetBackClick()
            }
            SET_OPTION -> {
                /*pendingOptions => selectedOptions*/
                onSheetBackClick()
            }
        }
    }

}