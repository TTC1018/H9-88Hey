package com.softeer.data.mapper

import com.softeer.data.model.TrimBasicOptionDto
import com.softeer.data.model.TrimBasicSubOptionDto
import com.softeer.domain.model.CarBasicOption
import com.softeer.domain.model.CarBasicSubOption

fun TrimBasicOptionDto.asEntity(): CarBasicOption =
    CarBasicOption(
        category = category,
        subOptions = subOptions.map(TrimBasicSubOptionDto::asEntity)
    )

fun TrimBasicSubOptionDto.asEntity(): CarBasicSubOption =
    CarBasicSubOption(
        name = name,
        imageUrl = imageUrl,
        description = description
    )