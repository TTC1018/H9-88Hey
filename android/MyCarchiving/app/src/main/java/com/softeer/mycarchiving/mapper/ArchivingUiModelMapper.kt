package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.CarFeed
import com.softeer.domain.model.SelectSimpleOption
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.archiving.SearchOptionUiModel
import com.softeer.mycarchiving.model.common.CarFeedUiModel

fun CarFeed.asUiModel() =
    CarFeedUiModel(
        id = id,
        model = modelName,
        isPurchase = purchased,
        creationDate = createdDate,
        trim = trim.name,
        trimOptions = listOf(engine, bodyType, wheelDrive).map { it.optionName },
        interiorColor = interiorColor.name,
        exteriorColor = exteriorColor.name,
        selectedOptions = selectedOptions.map { it.name },
        review = reviewText,
        tags = tags
    )

fun List<SelectSimpleOption>.asUiModel(): List<SearchOptionUiModel> =
    groupBy { it.category }
        .map { (category, options) ->
            SearchOptionUiModel(
                category = category,
                options = options.map(SelectSimpleOption::asUiModel)
            )
        }

fun SelectSimpleOption.asUiModel(): SearchOption =
    SearchOption(
        id = id,
        name = name
    )