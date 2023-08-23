package com.softeer.domain.model

data class MyArchiveFeed(
    val id: Long,
    val lastModifiedDate: String,
    val isSaved: Boolean,
    val totalPrice: Int,
    val carImageUrl: String,
    val modelName: String,
    val trim: String,
    val engine: String,
    val bodyType: String,
    val wheelDrive: String,
    val exteriorColor: MyArchiveFeedSimpleColor,
    val interiorColor: MyArchiveFeedSimpleColor,
    val selectedOptions: List<MyArchiveFeedOption>
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
    val subOptions: List<String>
)
