package com.softeer.mycarchiving.mapper

import com.softeer.data.CarColorType
import com.softeer.domain.model.CarExteriorColor
import com.softeer.domain.model.CarInteriorColor
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel

fun CarExteriorColor.asUiModel(): ColorOptionUiModel =
    ColorOptionUiModel(
        category = CarColorType.EXTERIOR,
        optionName = name,
        imageUrl = imageUrl,
        price = price,
        matchingColors = subColors,
        tags = tags ?: emptyList()
    )

fun CarInteriorColor.asUiModel(): ColorOptionUiModel =
    ColorOptionUiModel(
        category = CarColorType.INTERIOR,
        optionName = name,
        imageUrl = imageUrl,
        price = 0,
        matchingColors = emptyList(),
        tags = tags ?: emptyList(),
    )