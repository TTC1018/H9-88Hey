package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.CarColorType
import com.softeer.domain.model.CarExteriorColor
import com.softeer.domain.model.CarExteriorSimpleColor
import com.softeer.domain.model.CarInteriorColor
import com.softeer.domain.model.CarInteriorSimpleColor
import com.softeer.mycarchiving.model.makingcar.ColorOptionSimpleUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel

fun CarExteriorColor.asUiModel(): ColorOptionUiModel =
    ColorOptionUiModel(
        id = id,
        category = CarColorType.EXTERIOR,
        optionName = name,
        carImagePath = carImagePath,
        imageUrl = imageUrl,
        price = price,
        matchingColors = subColors,
        tags = tags ?: emptyList()
    )

fun ColorOptionSimpleUiModel.asExteriorEntity(): CarExteriorSimpleColor =
    CarExteriorSimpleColor(
        id = id,
        name = colorName,
        carImageUrl = carImageUrl ?: "",
        price = price ?: 0,
        colorImageUrl = imageUrl,
    )

fun ColorOptionSimpleUiModel.asInteriorEntity(): CarInteriorSimpleColor =
    CarInteriorSimpleColor(
        id = id,
        name = colorName,
        colorImageUrl = imageUrl,
    )

fun CarInteriorColor.asUiModel(): ColorOptionUiModel =
    ColorOptionUiModel(
        id = id,
        category = CarColorType.INTERIOR,
        optionName = name,
        imageUrl = imageUrl,
        price = 0,
        matchingColors = emptyList(),
        tags = tags ?: emptyList(),
    )