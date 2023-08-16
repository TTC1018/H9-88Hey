package com.softeer.mycarchiving.ui.archiving

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(): ViewModel() {

    private val feed = CarFeedUiModel(
        model = "팰리세이드",
        isPurchase = false,
        creationDate = "2023-07-19",
        trim = "Le Blanc",
        trimOptions = listOf("디젤 2.2", "4WD", "7인승"),
        interiorColor = "문라이트 블루 펄",
        exteriorColor = "퀄팅 천연(블랙)",
        selectedOptions = listOf("컴포트 || 패키지", "듀얼 와이드 선루프"),
        review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
        tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘", "대형견도 문제 없어요🐶")
    )

    private val _showSearchSheet = MutableStateFlow(false)
    val showSearchSheet: StateFlow<Boolean> = _showSearchSheet

    private val _currentSheetPage = MutableStateFlow(SEARCH_CONDITION)
    val currentSheetPage: StateFlow<ArchiveSearchPage> = _currentSheetPage

    private val _selectedCarName = MutableStateFlow("펠리세이드")
    val selectedCarName: StateFlow<String> = _selectedCarName
    val pendingCarName = MutableStateFlow(_selectedCarName.value)
    val selectedOptions = MutableStateFlow(mutableListOf("컴포트 || 패키지", "듀얼 와이드 선루프"))
    val pendingOptions =  MutableStateFlow(mutableListOf("컴포트 || 패키지", "듀얼 와이드 선루프", "컴포트 2 패키지", "듀얼 와이드 선루프"))

    private val _carFeeds = MutableStateFlow(listOf(feed, feed, feed, feed, feed, feed, feed))
    val carFeeds: StateFlow<List<CarFeedUiModel>> = _carFeeds

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