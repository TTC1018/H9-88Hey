package com.softeer.mycarchiving.util

import com.softeer.mycarchiving.model.common.ProgressChildUiModel
import com.softeer.mycarchiving.model.common.ProgressUiModel

object MakeCarProcess {

    private val trimSelect = ProgressUiModel(
        id = 0,
        name = "트림 선택",
        children = listOf(
            ProgressChildUiModel(0, "엔진" , 14),
            ProgressChildUiModel(1, "바디타입", 14),
            ProgressChildUiModel(2, "구동방식", 14),
        ),
        needNoChild = true
    )

    private val colorSelect = ProgressUiModel(
        id = 1,
        name = "색상 선택",
        children = listOf(
            ProgressChildUiModel(0, "외장색상", 14),
            ProgressChildUiModel(1, "내장색상", 14),
        )
    )

    private val optionSelect = ProgressUiModel(
        id = 2,
        name = "옵션 선택",
        children = listOf(
            ProgressChildUiModel(0, "선택옵션", 10),
            ProgressChildUiModel(1, "H Genuine Accessories", 10),
            ProgressChildUiModel(2, "N Performance", 10),
        )
    )

    val makeCarProcess = listOf(trimSelect, colorSelect, optionSelect)
}