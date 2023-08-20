package com.softeer.data.mapper

import com.softeer.data.model.ArchivingExteriorDto
import com.softeer.data.model.ArchivingFeedDto
import com.softeer.data.model.ArchivingInteriorDto
import com.softeer.data.model.ArchivingModelDto
import com.softeer.data.model.ArchivingSelectOptionDto
import com.softeer.data.model.ArchivingSelectedDto
import com.softeer.data.model.ArchivingTrimOptionDto
import com.softeer.domain.model.CarExteriorSimpleColor
import com.softeer.domain.model.CarExtraOption
import com.softeer.domain.model.CarExtraSimpleOption
import com.softeer.domain.model.CarFeed
import com.softeer.domain.model.CarInteriorSimpleColor
import com.softeer.domain.model.ModelOption
import com.softeer.domain.model.SelectSimpleOption
import com.softeer.domain.model.TrimSimpleOption

fun ArchivingFeedDto.asEntity(): CarFeed =
    CarFeed(
        id = id,
        modelName = modelName,
        createdDate = createdDate,
        reviewText = review,
        totalPrice = totalPrice,
        tags = tags,
        trim = trim.asEntity(),
        engine = engine.asEntity(),
        bodyType = bodyType.asEntity(),
        wheelDrive = wheelDrive.asEntity(),
        interiorColor = interiorColor.asEntity(),
        exteriorColor = exteriorColor.asEntity(),
        selectedOptions = selectedOptions.map(ArchivingSelectedDto::asEntity),
        purchased = purchased
    )

fun ArchivingModelDto.asEntity(): ModelOption =
    ModelOption(
        id = id,
        name = name,
        price = price,
        modelFeatures = emptyList()
    )

fun ArchivingTrimOptionDto.asEntity(): TrimSimpleOption =
    TrimSimpleOption(
        id = id,
        optionName = name,
        price = price
    )

fun ArchivingExteriorDto.asEntity(): CarExteriorSimpleColor =
    CarExteriorSimpleColor(
        id = id,
        name = name,
        price = price,
        carImageUrl = carImageUrl,
        colorImageUrl = colorImageUrl,
    )

fun ArchivingInteriorDto.asEntity(): CarInteriorSimpleColor =
    CarInteriorSimpleColor(
        id = id,
        name = name,
        colorImageUrl = colorImageUrl
    )

fun ArchivingSelectedDto.asEntity(): CarExtraSimpleOption =
    CarExtraSimpleOption(
        id = id,
        name = name,
        imageUrl = imageUrl,
        price = price,
        subOptions = subOptions,
        tags = tags,
    )

fun ArchivingSelectOptionDto.asEntity(): SelectSimpleOption =
    SelectSimpleOption(
        id = id,
        name = name,
        category = category
    )