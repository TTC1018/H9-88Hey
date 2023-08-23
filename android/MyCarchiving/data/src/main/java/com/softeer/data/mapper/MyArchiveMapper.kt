package com.softeer.data.mapper

import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarTempInfoBody
import com.softeer.data.model.MyArchiveExteriorDto
import com.softeer.data.model.MyArchiveInteriorDto
import com.softeer.data.model.MyArchiveMadeFeedDto
import com.softeer.data.model.MyArchiveSelectedDto
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.model.MyArchiveFeedOption
import com.softeer.domain.model.MyArchiveFeedSimpleColor

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
        lastModifiedDate = lastModifiedDate,
        isSaved = isSaved,
        carImageUrl = exteriorColor?.carImageUrl,
        modelName = model.name,
        trim = trim?.name,
        engine = engine?.name,
        bodyType = bodyType?.name,
        wheelDrive = wheelDrive?.name,
        exteriorColor = exteriorColor?.asEntity(),
        interiorColor = interiorColor?.asEntity(),
        selectedOptions = selectedOptions.map(MyArchiveSelectedDto::asEntity)
    )

fun MyArchiveExteriorDto.asEntity(): MyArchiveFeedSimpleColor =
    MyArchiveFeedSimpleColor(
        id = id,
        name = name,
        colorImageUrl = colorImageUrl
    )

fun MyArchiveInteriorDto.asEntity(): MyArchiveFeedSimpleColor =
    MyArchiveFeedSimpleColor(
        id = id,
        name = name,
        colorImageUrl = colorImageUrl
    )

fun MyArchiveSelectedDto.asEntity(): MyArchiveFeedOption =
    MyArchiveFeedOption(
        id = id,
        name = name,
        imageUrl = imageUrl,
        subOptions = subOptions
    )