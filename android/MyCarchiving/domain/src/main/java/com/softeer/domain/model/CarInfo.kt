package com.softeer.domain.model

data class CarInfo(
    val infoId: Long,
    val modelId: Int,
    val bodyTypeId: Int,
    val wheelTypeId: Int,
    val engineId: Int,
    val exteriorColorId: Int,
    val interiorColorId: Int,
    val selectOptionsIds: List<String>
)

data class CarTempInfo(
    val infoId: Long? = null,
    val modelId: Int? = null,
    val bodyTypeId: Int? = null,
    val wheelTypeId: Int? = null,
    val engineId: Int? = null,
    val exteriorColorId: Int? = null,
    val interiorColorId: Int? = null,
    val selectOptionsIds: List<String>? = null
)