package com.softeer.mycarchiving.model.common

import com.softeer.mycarchiving.model.archiving.SearchOption

data class CarFeedUiModel(
    val id: Long,
    val model: String,
    val isPurchase: Boolean,
    val creationDate: String,
    val trim: String,
    val trimOptions: List<String>,
    val interiorColor: String,
    val exteriorColor: String,
    val selectedOptions: List<SearchOption>? = null,
    val review: String? = null,
    val tags: List<String>? = null,
)
