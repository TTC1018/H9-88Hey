package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.MyArchiveFeedOption
import com.softeer.domain.model.MyArchiveFeedSimpleColor
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedColorUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel

fun MyArchiveFeed.asUiModel(): ArchiveFeedUiModel =
    ArchiveFeedUiModel(
        id = id,
        date = date,
        isSavedOrPurchase = isSavedOrPurchase,
        totalPrice = totalPrice,
        carImageUrl = carImageUrl,
        modelName = modelName,
        trimName = trim,
        trimOptions = listOfNotNull(engine, bodyType, wheelDrive),
        exteriorColor = exteriorColor?.asUiModel(),
        interiorColor = interiorColor?.asUiModel(),
        review = review,
        tags = tags,
        selectedOptions = selectedOptions.map(MyArchiveFeedOption::asUiModel)
    )

fun MyArchiveFeedSimpleColor.asUiModel(): ArchiveFeedColorUiModel =
    ArchiveFeedColorUiModel(
        name = name,
        colorImageUrl = colorImageUrl
    )

fun MyArchiveFeedOption.asUiModel(): ArchiveFeedSelectedOptionUiModel =
    ArchiveFeedSelectedOptionUiModel(
        name = name,
        imageUrl = imageUrl,
        subOptions = subOptions,
        tags = tags
    )