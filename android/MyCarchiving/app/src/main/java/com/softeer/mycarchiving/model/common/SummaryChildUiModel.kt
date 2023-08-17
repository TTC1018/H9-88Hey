package com.softeer.mycarchiving.model.common

data class SummaryChildUiModel(
    val name: String,
    val colorPosition: String? = null,
    val price: String? = null,
    val imageUrl: String? = null,
    val needPlus: Boolean = true
)