package com.softeer.mycarchiving.model.myarchive

data class MadeCarUiModel(
    val id: Long,
    val lastModifiedDate: String,
    val isSaved: Boolean,
    val totalPrice: Int,
    val carImageUrl: String? = null,
    val modelName: String,
    val trimName: String? = null,
    val trimOptions: List<String?>,
    val exteriorColor: MadeCarColorUiModel? = null,
    val interiorColor: MadeCarColorUiModel? = null,
    val selectedOptions: List<MadeCarSelectedOptionUiModel>
)

data class MadeCarColorUiModel(
    val name: String,
    val colorImageUrl: String
)

data class MadeCarSelectedOptionUiModel(
    val name: String,
    val imageUrl: String,
    val subOptions: List<String>? = null
)
