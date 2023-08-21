package com.softeer.data.mapper

import com.softeer.data.model.CarInfoBody
import com.softeer.data.model.CarTempInfoBody
import com.softeer.domain.model.CarInfo
import com.softeer.domain.model.CarTempInfo

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