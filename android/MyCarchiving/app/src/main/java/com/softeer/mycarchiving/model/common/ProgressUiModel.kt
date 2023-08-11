package com.softeer.mycarchiving.model.common

data class ProgressUiModel(
    val id: Int,
    val name: String,
    val children: List<ProgressChildUiModel> = listOf(),
    val needNoChild: Boolean = false
)