package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.MyArchiveFeedOption
import com.softeer.domain.model.MyArchiveFeedSimpleColor
import com.softeer.mycarchiving.model.myarchive.MadeCarColorUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarUiModel

fun MyArchiveFeed.asUiModel(): MadeCarUiModel =
    MadeCarUiModel(
        id = id.toString(),
        lastModifiedDate = lastModifiedDate,
        isSaved = isSaved,
        carImageUrl = carImageUrl,
        modelName = modelName,
        trimName = trim,
        trimOptions = listOf(engine, bodyType, wheelDrive),
        exteriorColor = exteriorColor?.asUiModel(),
        interiorColor = interiorColor?.asUiModel(),
        selectedOptions = selectedOptions.map(MyArchiveFeedOption::asUiModel)
    )

fun MyArchiveFeedSimpleColor.asUiModel(): MadeCarColorUiModel =
    MadeCarColorUiModel(
        name = name,
        colorImageUrl = colorImageUrl
    )

fun MyArchiveFeedOption.asUiModel(): MadeCarSelectedOptionUiModel =
    MadeCarSelectedOptionUiModel(
        name = name,
        imageUrl = imageUrl,
        subOptions = subOptions
    )