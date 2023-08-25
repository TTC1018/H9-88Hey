package com.softeer.mycarchiving.model.myarchive

data class ArchiveFeedUiModel(
    val id: Long,
    val date: String,
    val isSavedOrPurchase: Boolean,
    val totalPrice: Int,
    val carImageUrl: String? = null,
    val modelName: String,
    val trimName: String? = null,
    val trimOptions: List<String>,
    val exteriorColor: ArchiveFeedColorUiModel? = null,
    val interiorColor: ArchiveFeedColorUiModel? = null,
    val review: String? = null,
    val tags: List<String>? = null,
    val selectedOptions: List<ArchiveFeedSelectedOptionUiModel>
)

data class ArchiveFeedColorUiModel(
    val name: String,
    val colorImageUrl: String
)

data class ArchiveFeedSelectedOptionUiModel(
    val name: String,
    val imageUrl: String,
    val subOptions: List<String>? = null,
    val tags: List<String>? = null
)
