package com.softeer.data.mapper

import com.softeer.data.model.ModelFeatureDto
import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimModelDto
import com.softeer.data.model.TrimWheelDto
import com.softeer.domain.model.TrimModelFeature
import com.softeer.domain.model.TrimModelOption
import com.softeer.domain.model.TrimOption

fun TrimModelDto.asEntity() =
    TrimModelOption(
        id = id,
        name = name,
        price = price,
        modelFeatures = modelFeatures.map(ModelFeatureDto::asEntity)
    )

fun ModelFeatureDto.asEntity() =
    TrimModelFeature(
        name = name,
        imageUrl = imageUrl
    )

fun TrimEngineDto.asEntity() =
    TrimOption(
        id = id,
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
        maximumOutput = maximumPower,
        maximumTorque = maximumTorque
    )

fun TrimBodyTypeDto.asEntity() =
    TrimOption(
        id = id,
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
    )

fun TrimWheelDto.asEntity() =
    TrimOption(
        id = id,
        modelId = modelId,
        optionName = name,
        optionDesc = description,
        imageUrl = imageUrl,
        price = price,
    )
