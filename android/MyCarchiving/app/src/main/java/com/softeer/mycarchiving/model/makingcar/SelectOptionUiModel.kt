package com.softeer.mycarchiving.model.makingcar

data class SelectOptionUiModel(
    val isAvailable: Boolean? = true,
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val tags: List<String>? = null,
    val subOptions: List<SubSelectOptionUiModel>? = null
) {
    fun asSimpleUiModel() =
        SelectOptionSimpleUiModel(
            id = id,
            name = name,
            price = price,
        )
}

data class SelectOptionSimpleUiModel(
    val id: String,
    val name: String,
    val price: Int,
)
