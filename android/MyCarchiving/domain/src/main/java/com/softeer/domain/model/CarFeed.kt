package com.softeer.domain.model

data class CarFeed(
    val id: String,
    val modelName: String,
    val createdDate: String,
    val reviewText: String,
    val totalPrice: Int,
    val tags: List<String>,
    val trim: ModelOption,
    val engine: TrimSimpleOption,
    val bodyType: TrimSimpleOption,
    val wheelDrive: TrimSimpleOption,
    val interiorColor: CarInteriorSimpleColor,
    val exteriorColor: CarExteriorSimpleColor,
    val selectedOptions: List<CarExtraSimpleOption>,
    val purchased: Boolean
)
