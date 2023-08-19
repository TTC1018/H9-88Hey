package com.softeer.domain.model

sealed class CarColor(
    open val id: Int,
    open val name: String,
    open val imageUrl: String,
    open val tags: List<String>?
)

data class CarExteriorColor(
    override val id: Int,
    override val name: String,
    val carImagePath: String,
    override val imageUrl: String,
    val price: Int,
    val subColors: List<Int>,
    override val tags: List<String>?
): CarColor(id, name, imageUrl, tags)

data class CarInteriorColor(
    override val id: Int,
    override val name: String,
    val carImageUrl: String,
    override val imageUrl: String,
    override val tags: List<String>?
): CarColor(id,  name, imageUrl, tags)