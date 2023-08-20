package com.softeer.mycarchiving.ui.myarchive.main

import androidx.lifecycle.ViewModel
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

    private val _madeCars = MutableStateFlow(
        listOf(
            MadeCarUiModel(
                id = "123",
                modelName = "팰리세이드",
                trimName = "Le Blanc",
                isSaved = false,
                lastModifiedDate = "2023-07-19",
                trimOptions = listOf(
                    "디젤 2.2", "4WD", "7인승"
                ),
                selectedOptions = listOf(
                    MadeCarSelectedOptionUiModel("컴포트 II", "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/roa.jpg"),
                    MadeCarSelectedOptionUiModel("듀얼 와이드 선루프", "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/dualwidesunroof.jpg"),
                    MadeCarSelectedOptionUiModel("현대스마트센스 I", "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/fca2.jpg")
                ),
            ),
            MadeCarUiModel(
                id = "124",
                modelName = "팰리세이드",
                trimName = "Le Blanc",
                isSaved = true,
                lastModifiedDate = "2023-07-18",
                trimOptions = listOf(
                    "디젤 2.2", "2WD", "8인승"
                ),
                selectedOptions = listOf(
                    MadeCarSelectedOptionUiModel("적외선 무릎워머", ""),
                    MadeCarSelectedOptionUiModel("빌트인 공기청정기", ""),
                    MadeCarSelectedOptionUiModel("사이드 스텝", "")
                ),
            )
        )
    )
    val madeCars: StateFlow<List<MadeCarUiModel>> = _madeCars

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun deleteMadeCar(deleteIndex: Int) {
        _madeCars.value = _madeCars.value.toMutableList().apply { removeAt(deleteIndex) }
    }

}