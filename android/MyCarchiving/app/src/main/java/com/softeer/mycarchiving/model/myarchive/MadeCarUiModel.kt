package com.softeer.mycarchiving.model.myarchive

data class MadeCarUiModel(
    val id: String,
    val modelName: String,
    val isSaved: Boolean,
    val trimName: String,
    val trimOptions: List<String>,
    val lastModifiedDate: String,
    val selectedOptions: List<MadeCarSelectedOptionUiModel>
)

data class MadeCarSelectedOptionUiModel(
    val name: String,
    val imageUrl: String
)
