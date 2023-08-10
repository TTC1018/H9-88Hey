package com.softeer.mycarchiving.ui.makingcar.selecttrim

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.OptionCardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SelectTrimViewModel @Inject constructor() : ViewModel() {

    private val _options = MutableStateFlow(
        listOf(
            OptionCardUiModel(
                optionName = "디젤 2.2",
                optionDesc = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.",
                price = 1480000,
                maximumOutput = "202/3,800PS/rpm",
                maximumTorque = "45.0/1,750~2,750kgf-m/rpm",
            ),
            OptionCardUiModel(
                optionName = "가솔린 3.8",
                optionDesc = "고마력의 우수한 가속 성능을 확보하여, 넉넉하고 안정감 있는 주행이 가능합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다.",
                price = 0,
                maximumOutput = "202/3,800PS/rmp",
                maximumTorque = "36.2/5,200kgf-m/rpm",
            )
        )
    )
    val options: StateFlow<List<OptionCardUiModel>> = _options

}