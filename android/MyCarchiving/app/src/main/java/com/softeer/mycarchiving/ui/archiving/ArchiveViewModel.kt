package com.softeer.mycarchiving.ui.archiving

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.archiving.SearchOptionUiModel
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

    private val _ableCars = MutableStateFlow(
        listOf(
            SearchOptionUiModel(
                category = "수소 / 전기차",
                options = listOf(SearchOption(name = "넥쏘"), SearchOption(name = "디 올 뉴 코나 Electric"), SearchOption(name = "아이오닉 6"), SearchOption(name = "포터 I Electric"), SearchOption(name = "포터 II Electric"), SearchOption(name = "포터 II Electric 특장차"))
            ),
            SearchOptionUiModel(
                category = "승용차",
                options = listOf(SearchOption(name = "쏘나타 디 엣지"), SearchOption(name = "쏘나타 디 엣지 Hybrid"), SearchOption(name = "더 뉴 아반떼"), SearchOption(name = "더 뉴 아반떼 Hybrid"), SearchOption(name = "디 올 뉴 그랜저"), SearchOption(name = "디 올 뉴 그랜저 Hybrid"))
            ),
            SearchOptionUiModel(
                category = "SUV",
                options = listOf(SearchOption(name = "팰리세이드"), SearchOption(name = "베뉴"), SearchOption(name = "디 올 뉴 코나"), SearchOption(name = "디 올 뉴 코나 Hybrid"), SearchOption(name = "투싼"), SearchOption(name = "투싼 Hybrid"))
            ),
        )
    )
    val ableCars: StateFlow<List<SearchOptionUiModel>> = _ableCars

    private val _selectedCar = MutableStateFlow(SearchOption(name = "펠리세이드"))
    val selectedCar: StateFlow<SearchOption> = _selectedCar
    val pendingCarName = MutableStateFlow(_selectedCar.value)

    private val _ableOptions = MutableStateFlow(
        listOf(
            SearchOptionUiModel(
                category = "선택 옵션",
                options = listOf(SearchOption(name = "주차 보조 시스템 ||"), SearchOption(name = "주차 보조 시스템 ||"), SearchOption(name = "주차 보조 시스템 ||"), SearchOption(name = "주차 보조 시스템 ||"))
            ),
            SearchOptionUiModel(
                category = "H Genuine Accessories",
                options = listOf(SearchOption(name = "듀얼 머플러 패키지"), SearchOption(name = "사이드스텝"), SearchOption(name = "빌트인 공기청정기"), SearchOption(name = "듀얼 머플러 패키지"), SearchOption(name = "사이드스텝"), SearchOption(name = "빌트인 공기청정기"))
            ),
            SearchOptionUiModel(
                category = "N Performance",
                options = listOf(SearchOption(name = "20인치 다크 스퍼터링 휠"), SearchOption(name = "20인치 블랙톤 전면 가공휠"), SearchOption(name = "알콘 단조브레이크 휠 패키지"))
            )
        )
    )
    val ableOptions: StateFlow<List<SearchOptionUiModel>> = _ableOptions

    val totalOptionsSize = MutableStateFlow(0)

    private val _appliedOptions = MutableStateFlow(emptyList<SearchOption>())
    val appliedOptions: StateFlow<List<SearchOption>> = _appliedOptions

    private val _selectedOptions = MutableStateFlow(emptyList<SearchOption>())
    val selectedOptions: StateFlow<List<SearchOption>> = _selectedOptions

    private val _pendingOptions =  MutableStateFlow(emptyList<SearchOption>())
    val pendingOptions: StateFlow<List<SearchOption>> = _pendingOptions

    private val _carFeeds = MutableStateFlow(listOf(feed, feed, feed, feed, feed, feed, feed))
    val carFeeds: StateFlow<List<CarFeedUiModel>> = _carFeeds

    fun openSearchSheet() {
        initAbleOptions()
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
        when(currentSheetPage.value) {
            SEARCH_CONDITION -> {
                /*검색 API에 적용*/
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
        var totalSize = 0
        for(ableOption in _ableOptions.value) {
            for (option in ableOption.options) {
                option.isSelect = _selectedOptions.value.contains(option)
                totalSize++
            }
        }
        totalOptionsSize.value = totalSize
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