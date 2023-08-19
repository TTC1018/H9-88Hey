package com.softeer.data.mapper

import com.softeer.data.model.TrimSelectOptionDto
import com.softeer.data.model.TrimSubOptionDto
import com.softeer.domain.model.CarExtraOption
import com.softeer.domain.model.CarExtraSubOption

fun TrimSelectOptionDto.asEntity(): CarExtraOption =
    CarExtraOption(
        isAvailable = isAvailable,
        id = id,
        name = name,
        imageUrl = imageUrl,
        price = price,
        tags = tags,
        subOptions = subOptions.map(TrimSubOptionDto::asEntity)
    )

fun TrimSubOptionDto.asEntity(): CarExtraSubOption =
    CarExtraSubOption(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description
    )