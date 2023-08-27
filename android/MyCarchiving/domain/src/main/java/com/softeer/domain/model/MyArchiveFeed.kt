package com.softeer.domain.model

data class MyArchiveFeed(
    val id: String,
    val date: String,
    val isSavedOrPurchase: Boolean,
    val totalPrice: Int,
    val carImageUrl: String? = null,
    val modelName: String,
    val trim: ModelOption? = null,
    val engine: TrimSimpleOption? = null,
    val bodyType: TrimSimpleOption? = null,
    val wheelDrive: TrimSimpleOption? = null,
    val exteriorColor: CarExteriorSimpleColor? = null,
    val interiorColor: CarInteriorSimpleColor? = null,
    val review: String? = null,
    val tags: List<String>? = null,
    val selectedOptions: List<MyArchiveFeedOption>,
)

data class MyArchiveFeedSimpleColor(
    val id: Int,
    val name: String,
    val price: Int? = null,
    val carImageUrl: String? = null,
    val colorImageUrl: String
)

data class MyArchiveFeedOption(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: Int,
    val subOptions: List<String>? = null,
    val tags: List<String>? = null
)
