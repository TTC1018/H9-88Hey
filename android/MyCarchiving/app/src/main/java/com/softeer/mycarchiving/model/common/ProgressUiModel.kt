package com.softeer.mycarchiving.model.common

data class ProgressUiModel(
    val id: Int,
    val name: String,
    val children: List<ProgressChildUiModel>,
    val needNoChildProgress: Boolean = false
)