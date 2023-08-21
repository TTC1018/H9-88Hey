package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.ModelOption
import com.softeer.domain.model.TrimOption
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.makingcar.ModelFeatureUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel

fun ModelOption.asSelectModelUiModel(): SelectModelUiModel =
    SelectModelUiModel(
        name = name,
        price = price,
        features = modelFeatures.map { featureDto ->
            ModelFeatureUiModel(
                name = featureDto.name,
                imageUrl = featureDto.imageUrl
            )
        }
    )

fun TrimOption.asUiModel() =
    TrimOptionUiModel(
        id = id,
        optionName = optionName,
        optionDesc = optionDesc,
        imageUrl = imageUrl,
        price = price,
        maximumOutput = maximumOutput,
        maximumTorque = maximumTorque,
    )