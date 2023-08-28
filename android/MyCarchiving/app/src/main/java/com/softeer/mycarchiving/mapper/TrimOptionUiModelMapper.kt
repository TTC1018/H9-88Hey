package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.ModelFeature
import com.softeer.domain.model.ModelOption
import com.softeer.domain.model.TrimOption
import com.softeer.domain.model.TrimSimpleOption
import com.softeer.mycarchiving.model.TrimOptionSimpleUiModel
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.makingcar.ModelFeatureUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel

fun ModelOption.asSelectModelUiModel(): SelectModelUiModel =
    SelectModelUiModel(
        id = id,
        name = name,
        price = price,
        features = modelFeatures.map { entity ->
            ModelFeatureUiModel(
                name = entity.name,
                imageUrl = entity.imageUrl
            )
        }
    )

fun SelectModelUiModel.asModelEntity() =
    ModelOption(
        id = id,
        name = name,
        price = price,
        modelFeatures = features.map(ModelFeatureUiModel::asEntity)
    )

fun ModelFeatureUiModel.asEntity() =
    ModelFeature(
        name = name,
        imageUrl = imageUrl
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

fun TrimSimpleOption.asUiModel() =
    TrimOptionSimpleUiModel(
        id = id,
        name = optionName,
        price = price
    )

fun TrimOptionSimpleUiModel.asEntity() =
    TrimSimpleOption(
        id = id,
        optionName = name,
        price = price
    )