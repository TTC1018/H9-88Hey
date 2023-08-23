package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.CarBasicOption
import com.softeer.domain.model.CarBasicSubOption
import com.softeer.domain.model.CarExtraOption
import com.softeer.domain.model.CarExtraSimpleOption
import com.softeer.domain.model.CarExtraSubOption
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.model.common.CarBasicUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionSimpleUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel

fun CarExtraOption.asUiModel() =
    SelectOptionUiModel(
        isAvailable = isAvailable,
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        tags = tags,
        subOptions = subOptions.map { it.asUiModel() }
    )

fun CarExtraSimpleOption.asSimpleUiModel() =
    SelectOptionSimpleUiModel(
        id = id,
        name = name,
        price = price,
    )

fun CarExtraSubOption.asUiModel() =
    SubSelectOptionUiModel(
        name = name,
        imageUrl = imageUrl,
        description = description,
    )

fun CarBasicOption.asUiModel() =
    CarBasicUiModel(
        category = category,
        detailItems = subOptions.map { it.asUiModel() }
    )

fun CarBasicSubOption.asUiModel() =
    CarBasicDetailUiModel(
        name = name,
        description = description,
        imageUrl = imageUrl
    )