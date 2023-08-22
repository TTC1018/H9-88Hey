package com.softeer.mycarchiving.mapper

import com.softeer.domain.model.CarDetails
import com.softeer.domain.model.CarExteriorSimpleColor
import com.softeer.domain.model.CarExtraSimpleOption
import com.softeer.domain.model.CarFeed
import com.softeer.domain.model.CarInteriorSimpleColor
import com.softeer.domain.model.SelectSimpleOption
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.archiving.SearchOptionUiModel
import com.softeer.mycarchiving.model.common.CarDetailSelectOptionUiModel
import com.softeer.mycarchiving.model.common.CarDetailsUiModel
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionSimpleUiModel

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
        selectedOptions = selectedOptions.map(CarExtraSimpleOption::asUiModel),
        review = reviewText,
        tags = tags
    )

fun CarDetails.asUiModel() =
    CarDetailsUiModel(
        id = id,
        model = modelName,
        trim = trim.name,
        price = totalPrice,
        trimOptions = listOf(engine, bodyType, wheelDrive).map { it.optionName },
        interiorColor = interiorColor.asUiModel(),
        exteriorColor = exteriorColor.asUiModel(),
        selectedOptions = selectedOptions.map(CarExtraSimpleOption::asDetailUiModel),
        review = reviewText,
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

fun CarExtraSimpleOption.asUiModel(): SearchOption =
    SearchOption(
        id = id,
        name = name
    )

fun CarInteriorSimpleColor.asUiModel(): ColorOptionSimpleUiModel =
    ColorOptionSimpleUiModel(
        "내장",
        imageUrl = colorImageUrl,
        colorName = name
    )

fun CarExteriorSimpleColor.asUiModel(): ColorOptionSimpleUiModel =
    ColorOptionSimpleUiModel(
        "외장",
        carImageUrl = carImageUrl,
        imageUrl = colorImageUrl,
        colorName = name
    )

fun CarExtraSimpleOption.asDetailUiModel(): CarDetailSelectOptionUiModel =
    CarDetailSelectOptionUiModel(
        id = id,
        name = name,
        imageUrl = imageUrl,
        reviewText = review,
        subOptions = subOptions,
        tags = tags,
    )