package com.softeer.data.mapper

import com.softeer.data.model.TrimExteriorCarColor
import com.softeer.data.model.TrimInteriorCarColor
import com.softeer.domain.model.CarExteriorColor
import com.softeer.domain.model.CarInteriorColor

fun TrimExteriorCarColor.asEntity() =
    CarExteriorColor(
        id = id,
        name = name,
        carImagePath = carImagePath,
        imageUrl= colorImageUrl,
        price= additionalPrice,
        subColors = subInteriors,
        tags = tags
    )

fun TrimInteriorCarColor.asEntity() =
    CarInteriorColor(
        id = id,
        name = name,
        carImageUrl = carImageUrl,
        imageUrl= colorImageUrl,
        tags = tags
    )