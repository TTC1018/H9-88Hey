package com.softeer.mycarchiving.ui.makingcar.selectcolor

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SelectColorViewModel @Inject constructor() : ViewModel() {

    private val _imageUrls = MutableStateFlow<List<String>>(listOf(
        "https://search.pstatic.net/common?quality=75&direct=true&src=https%3A%2F%2Fimgauto-phinf.pstatic.net%2F20230602_173%2Fauto_1685665403248DsvnU_PNG%2F20230602092313_Nsn3iOYW.png",
        "https://search.pstatic.net/common?quality=75&direct=true&src=https%3A%2F%2Fimgauto-phinf.pstatic.net%2F20230602_245%2Fauto_1685665403343T05oH_PNG%2F20230602092313_G45bLup5.png",
        "https://search.pstatic.net/common?quality=75&direct=true&src=https%3A%2F%2Fimgauto-phinf.pstatic.net%2F20230602_253%2Fauto_1685665403434zMU7z_PNG%2F20230602092313_bBFffsPQ.png",
    ))
    val imageUrls: StateFlow<List<String>> = _imageUrls

    private val _topImageIndex = MutableStateFlow(0)
    val topImageIndex: StateFlow<Int> = _topImageIndex

    private val _selectedIndex = MutableStateFlow<Int>(0)
    val selectedIndex: StateFlow<Int> = _selectedIndex

    private val _colorOptions = MutableStateFlow<List<ColorOptionUiModel>>(listOf(
        ColorOptionUiModel(
            optionName = "어비스 블랙펄",
            imageUrl = "",
            price = 0,
            matchingColors = listOf(1, 2, 3),
            tags = listOf(
                "어린이\uD83E\uDDD2",
                "이것만 있으면 나도 주차 고수🚘",
                "편리해요😉",
                "대형견도 문제 없어요🐶",
                "큰 짐도 OK💼"
            ),
        )
    ))
    val colorOptions: StateFlow<List<ColorOptionUiModel>> = _colorOptions

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category

    fun changeTopImageIndex(beAdded: Boolean) {
        _topImageIndex.value =
            if (beAdded) {
                if (_topImageIndex.value == _imageUrls.value.size - 1) {
                    0
                } else {
                    _topImageIndex.value + 1
                }
            } else {
                if (_topImageIndex.value == 0) {
                    _imageUrls.value.size - 1
                } else {
                    _topImageIndex.value - 1
                }
            }
    }

}