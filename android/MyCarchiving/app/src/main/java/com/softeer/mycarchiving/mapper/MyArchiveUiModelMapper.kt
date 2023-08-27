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
        trim = trim?.asSelectModelUiModel(),
        trimOptions = listOfNotNull(engine, bodyType, wheelDrive).map { it.asUiModel() },
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
        id = id,
        name = name,
        imageUrl = imageUrl,
        price = price,
        subOptions = subOptions,
        tags = tags
    )