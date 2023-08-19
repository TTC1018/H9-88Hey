package com.softeer.domain.model

data class CarBasicOption(
    val category: String,
    val subOptions: List<CarBasicSubOption>
)

data class CarBasicSubOption(
    val name: String,
    val imageUrl: String,
    val description: String
)