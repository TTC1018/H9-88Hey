package com.softeer.mycarchiving.model

data class TrimOptionUiModel(
    val id: Int,
    val optionName: String,
    val optionDesc: String,
    val imageUrl: String,
    val price: Int,
    val maximumOutput: String? = null,
    val maximumTorque: String? = null,
) {
    fun asSimpleUiModel(): TrimOptionSimpleUiModel =
        TrimOptionSimpleUiModel(
            id = id,
            name = optionName,
            price = price
        )
}

data class TrimOptionSimpleUiModel(
    val id: Int,
    val name: String,
    val price: Int,
)