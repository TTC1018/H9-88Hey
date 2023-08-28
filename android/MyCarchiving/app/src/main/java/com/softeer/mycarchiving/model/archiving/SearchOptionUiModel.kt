package com.softeer.mycarchiving.model.archiving

data class SearchOptionUiModel(
    val category: String,
    val options: List<SearchOption>
)

data class SearchOption(
    val id: String = "",
    val name: String,
    var isSelect: Boolean = false
)
