package com.softeer.mycarchiving.model.common

data class CarFeedUiModel(
    val model: String,
    val isPurchase: Boolean,
    val creationDate: String,
    val trim: String,
    val trimOptions: List<String>,
    val interiorColor: String,
    val exteriorColor: String,
    val selectedOptions: List<String>? = null,
    val review: String? = null,
    val tags: List<String>? = null,
)
