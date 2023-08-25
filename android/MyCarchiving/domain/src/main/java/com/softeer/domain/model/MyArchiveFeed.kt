package com.softeer.domain.model

data class MyArchiveFeed(
    val id: Long,
    val date: String,
    val isSavedOrPurchase: Boolean,
    val totalPrice: Int,
    val carImageUrl: String? = null,
    val modelName: String,
    val trim: String? = null,
    val engine: String? = null,
    val bodyType: String? = null,
    val wheelDrive: String? = null,
    val exteriorColor: MyArchiveFeedSimpleColor? = null,
    val interiorColor: MyArchiveFeedSimpleColor? = null,
    val review: String? = null,
    val tags: List<String>? = null,
    val selectedOptions: List<MyArchiveFeedOption>,
)

data class MyArchiveFeedSimpleColor(
    val id: Int,
    val name: String,
    val colorImageUrl: String
)

data class MyArchiveFeedOption(
    val id: String,
    val name: String,
    val imageUrl: String,
    val subOptions: List<String>? = null,
    val tags: List<String>? = null
)
