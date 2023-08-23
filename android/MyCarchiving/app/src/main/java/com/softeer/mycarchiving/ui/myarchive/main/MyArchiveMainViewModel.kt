package com.softeer.mycarchiving.ui.myarchive.main

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MyArchiveMainViewModel @Inject constructor(): ViewModel() {

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex: StateFlow<Int> = _selectedIndex

    private val _madeCars = MutableStateFlow<List<MadeCarUiModel>>(
        listOf(
            MadeCarUiModel(
                id = "123",
                modelName = "팰리세이드",
                trimName = "Le Blanc",
                isSaved = false,
                lastModifiedDate = "2023-08-21",
                trimOptions = listOf(
                    "디젤 2.2", "4WD", "7인승"
                ),
                selectedOptions = listOf(
                    MadeCarSelectedOptionUiModel("컴포트 II", "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/roa.jpg"),
                    MadeCarSelectedOptionUiModel("듀얼 와이드 선루프", "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/dualwidesunroof.jpg"),
                    MadeCarSelectedOptionUiModel("현대스마트센스 I", "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/fca2.jpg")
                ),
            ),
        )
    )
    val madeCars: StateFlow<List<MadeCarUiModel>> = _madeCars

    private val _savedCars = MutableStateFlow(
        listOf(
            CarFeedUiModel(
                id = "1",
                model = "팰리세이드",
                isPurchase = false,
                creationDate = "2023-08-21",
                trim = "Le Blanc",
                trimOptions = listOf("디젤 2.2", "4WD", "7인승"),
                interiorColor = "퀄팅 천연(블랙)",
                exteriorColor = "어비스 블랙펄",
                selectedOptions = listOf(),
                review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
                tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘", "대형견도 문제 없어요🐶")
            ),
        )
    )
    val savedCars: StateFlow<List<CarFeedUiModel>> = _savedCars

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun deleteMadeCar(deleteIndex: Int) {
        _madeCars.value = _madeCars.value.toMutableList().apply { removeAt(deleteIndex) }
    }

    fun deleteSavedCar(deleteIndex: Int) {
        _savedCars.value = _savedCars.value.toMutableList().apply { removeAt(deleteIndex) }
    }

}