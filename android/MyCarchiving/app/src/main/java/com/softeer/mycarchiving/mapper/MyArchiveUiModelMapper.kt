package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.CarDetails
import com.softeer.domain.model.CarExtraSimpleOption
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.MyArchiveFeedOption
import com.softeer.domain.model.MyArchiveFeedSimpleColor
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedColorUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.util.TRIM_BODY_TYPE
import com.softeer.mycarchiving.util.TRIM_DRIVING_SYSTEM
import com.softeer.mycarchiving.util.TRIM_ENGINE

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

fun ArchiveFeedUiModel.asDetailEntity(): CarDetails =
    CarDetails(
        id = id,
        modelName = modelName,
        createdDate = date,
        reviewText = review ?: "",
        totalPrice = totalPrice,
        trim = trim?.asModelEntity(),
        engine = trimOptions.getOrNull(TRIM_ENGINE)?.asEntity(),
        bodyType = trimOptions.getOrNull(TRIM_BODY_TYPE)?.asEntity(),
        wheelDrive = trimOptions.getOrNull(TRIM_DRIVING_SYSTEM)?.asEntity(),
        interiorColor = interiorColor?.asInteriorEntity(),
        exteriorColor = exteriorColor?.asExteriorEntity(),
        selectedOptions = selectedOptions.map(ArchiveFeedSelectedOptionUiModel::asEntity),
        purchased = false,
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

fun ArchiveFeedSelectedOptionUiModel.asEntity(): CarExtraSimpleOption =
    CarExtraSimpleOption(
        id = id,
        name = name,
        imageUrl = imageUrl,
        review = "",
        price = price,
        subOptions = subOptions ?: emptyList(),
        tags = tags
    )

fun MyArchiveFeedOption.asSimpleUiModel(): CarExtraSimpleOption =
    CarExtraSimpleOption(
        id = id,
        name = name,
        imageUrl = imageUrl,
        price = price,
        review = "",
        subOptions = subOptions ?: emptyList(),
        tags = tags
    )