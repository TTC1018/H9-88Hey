package com.softeer.data.mapper

import com.softeer.data.model.ModelFeatureDto
import com.softeer.data.model.TrimBodyTypeDto
import com.softeer.data.model.TrimEngineDto
import com.softeer.data.model.TrimModelDto
import com.softeer.data.model.TrimWheelDto
import com.softeer.domain.model.ModelFeature
import com.softeer.domain.model.ModelOption
import com.softeer.domain.model.TrimOption

fun TrimModelDto.asEntity() =
    ModelOption(
        id = id,
        name = name,
        price = price,
        modelFeatures = modelFeatures.map(ModelFeatureDto::asEntity)
    )

fun ModelFeatureDto.asEntity() =
    ModelFeature(
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
