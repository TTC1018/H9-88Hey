package com.softeer.mycarchiving.util

import com.softeer.mycarchiving.model.common.ProgressChildUiModel
import com.softeer.mycarchiving.model.common.ProgressUiModel

const val TRIM_SELECT = 0
const val TRIM_ENGINE = 0
const val TRIM_BODY_TYPE = 1
const val TRIM_DRIVING_SYSTEM = 2

const val TRIM_COLOR = 1
const val TRIM_EXTERIOR = 0
const val TRIM_INTERIOR = 1

const val TRIM_OPTION = 2
const val TRIM_EXTRA = 0
const val TRIM_HGENUINE = 1
const val TRIM_NPERFORMANCE = 2

object MakeCarProcess {

    private val trimSelect = ProgressUiModel(
        id = TRIM_SELECT,
        name = "트림 선택",
        children = listOf(
            ProgressChildUiModel(TRIM_ENGINE, "엔진" , 14),
            ProgressChildUiModel(TRIM_BODY_TYPE, "바디타입", 14),
            ProgressChildUiModel(TRIM_DRIVING_SYSTEM, "구동방식", 14),
        ),
        needNoChild = true
    )

    private val colorSelect = ProgressUiModel(
        id = TRIM_COLOR,
        name = "색상 선택",
        children = listOf(
            ProgressChildUiModel(TRIM_EXTERIOR, "외장색상", 14),
            ProgressChildUiModel(TRIM_INTERIOR, "내장색상", 14),
        )
    )

    private val optionSelect = ProgressUiModel(
        id = TRIM_OPTION,
        name = "옵션 선택",
        children = listOf(
            ProgressChildUiModel(TRIM_EXTRA, "선택옵션", 10),
            ProgressChildUiModel(TRIM_HGENUINE, "H Genuine Accessories", 10),
            ProgressChildUiModel(TRIM_NPERFORMANCE, "N Performance", 10),
        )
    )

    val makeCarProcess = listOf(trimSelect, colorSelect, optionSelect)
}