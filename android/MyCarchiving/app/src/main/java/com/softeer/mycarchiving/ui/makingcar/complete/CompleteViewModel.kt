package com.softeer.mycarchiving.ui.makingcar.complete

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionSimpleUiModel
import com.softeer.mycarchiving.model.makingcar.CompleteOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CompleteViewModel @Inject constructor() : ViewModel() {

    private val _carName = MutableStateFlow("팰리세이드")
    val carName: StateFlow<String> = _carName

    private val _carImage = MutableStateFlow("")
    val carImage: StateFlow<String> = _carImage

    private val _modelName = MutableStateFlow("Le Blanc(르블랑)")
    val modelName: StateFlow<String> = _modelName

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    private val _colors = MutableStateFlow(
        listOf(
            ColorOptionSimpleUiModel(category = "외장", "", "문라이트 블루펄"),
            ColorOptionSimpleUiModel(category = "내장", "", "퀼팅 천연(블랙)")
        )
    )
    val colors: StateFlow<List<ColorOptionSimpleUiModel>> = _colors

    private val _trimOptions = MutableStateFlow(
        listOf(
            "디젤 2.2", "4WD", "7인승"
        )
    )
    val trimOptions: StateFlow<List<String>> = _trimOptions

    private val _selectedOptions = MutableStateFlow(
        listOf(
            CompleteOptionUiModel(
                optionName = "컴포트 II",
                price = 1090000,
                subOptionNames = listOf(
                    "후석 승객 알림", "메탈 리어 범퍼스텝",
                    "메탈 도어스커프", "3열 파워폴딩시트",
                    "3열 열선시트", "헤드업 디스플레이"
                )
            ),
            CompleteOptionUiModel(
                optionName = "컴포트 II",
                price = 1090000,
                subOptionNames = listOf(
                    "후석 승객 알림", "메탈 리어 범퍼스텝",
                    "메탈 도어스커프", "3열 파워폴딩시트",
                    "3열 열선시트", "헤드업 디스플레이"
                )
            )
        )
    )
    val selectedOptions: StateFlow<List<CompleteOptionUiModel>> = _selectedOptions

}