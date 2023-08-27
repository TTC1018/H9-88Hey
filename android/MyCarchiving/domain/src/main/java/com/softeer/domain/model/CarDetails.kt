package com.softeer.domain.model

data class CarDetails(
    val id: String,
    val modelName: String,
    val createdDate: String,
    val reviewText: String,
    val totalPrice: Int,
    val trim: ModelOption?,
    val engine: TrimSimpleOption?,
    val bodyType: TrimSimpleOption?,
    val wheelDrive: TrimSimpleOption?,
    val interiorColor: CarInteriorSimpleColor?,
    val exteriorColor: CarExteriorSimpleColor?,
    val selectedOptions: List<CarExtraSimpleOption>,
    val purchased: Boolean
)
