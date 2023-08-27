package com.softeer.data.mapper

import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarTempInfoBody
import com.softeer.data.model.MyArchiveExteriorDto
import com.softeer.data.model.MyArchiveInteriorDto
import com.softeer.data.model.MyArchiveMadeFeedDto
import com.softeer.data.model.MyArchiveSavedFeedDto
import com.softeer.data.model.MyArchiveSelectedDto
import com.softeer.data.model.MyArchiveTrimDto
import com.softeer.data.model.MyArchiveTrimOptionDto
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import com.softeer.domain.model.ModelOption
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.MyArchiveFeedOption
import com.softeer.domain.model.MyArchiveFeedSimpleColor
import com.softeer.domain.model.TrimSimpleOption

fun CarTempInfo.asBody(): CarTempInfoBody =
    CarTempInfoBody(
        myChivingId = infoId,
        bodyType = bodyTypeId,
        wheelType = wheelTypeId,
        engine = engineId,
        trim = modelId,
        exteriorColor = exteriorColorId,
        interiorColor = interiorColorId,
        selectOptions = selectOptionsIds
    )

fun CarInfo.asBody(): CarInfoBody =
    CarInfoBody(
        myChivingId = infoId,
        bodyType = bodyTypeId,
        wheelType = wheelTypeId,
        engine = engineId,
        trim = modelId,
        exteriorColor = exteriorColorId,
        interiorColor = interiorColorId,
        selectOptions = selectOptionsIds
    )

fun MyArchiveMadeFeedDto.asEntity(): MyArchiveFeed =
    MyArchiveFeed(
        id = id,
        date = lastModifiedDate,
        isSavedOrPurchase = isSaved,
        totalPrice = totalPrice,
        carImageUrl = exteriorColor?.carImageUrl,
        modelName = model.name,
        trim = trim?.asEntity(),
        engine = engine?.asEntity(),
        bodyType = bodyType?.asEntity(),
        wheelDrive = wheelDrive?.asEntity(),
        exteriorColor = exteriorColor?.asEntity(),
        interiorColor = interiorColor?.asEntity(),
        selectedOptions = selectedOptions.map(MyArchiveSelectedDto::asEntity)
    )

fun MyArchiveSavedFeedDto.asEntity(): MyArchiveFeed =
    MyArchiveFeed(
        id = id,
        date = creationDate,
        isSavedOrPurchase = purchase,
        totalPrice = totalPrice,
        carImageUrl = exteriorColor.colorImageUrl,
        modelName = modelName,
        trim = trim.asEntity(),
        engine = engine.asEntity(),
        bodyType = bodyType.asEntity(),
        wheelDrive = wheelDrive.asEntity(),
        exteriorColor = exteriorColor.asEntity(),
        interiorColor = interiorColor.asEntity(),
        review = review,
        tags = tags,
        selectedOptions = selectedOptions.map(MyArchiveSelectedDto::asEntity)
    )

fun MyArchiveExteriorDto.asEntity(): MyArchiveFeedSimpleColor =
    MyArchiveFeedSimpleColor(
        id = id,
        name = name,
        colorImageUrl = colorImageUrl,
        carImageUrl = carImageUrl,
        price = price,
    )

fun MyArchiveInteriorDto.asEntity(): MyArchiveFeedSimpleColor =
    MyArchiveFeedSimpleColor(
        id = id,
        name = name,
        colorImageUrl = colorImageUrl
    )

fun MyArchiveTrimOptionDto.asEntity(): TrimSimpleOption =
    TrimSimpleOption(
        id = id,
        optionName = name,
        price = price
    )

fun MyArchiveTrimDto.asEntity(): ModelOption =
    ModelOption(
        id = id,
        name = name,
        price = price,
        modelFeatures = emptyList(),
    )

fun MyArchiveSelectedDto.asEntity(): MyArchiveFeedOption =
    MyArchiveFeedOption(
        id = id,
        name = name,
        price = price,
        imageUrl = imageUrl,
        subOptions = subOptions,
        tags = tags
    )