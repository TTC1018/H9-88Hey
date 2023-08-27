package com.softeer.mycarchiving.model.myarchive

import com.softeer.mycarchiving.model.TrimOptionSimpleUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import kotlinx.serialization.Serializable

@Serializable
data class ArchiveFeedUiModel(
    val id: String,
    val date: String,
    val isSavedOrPurchase: Boolean,
    val totalPrice: Int,
    val carImageUrl: String? = null,
    val modelName: String,
    val trim: SelectModelUiModel? = null,
    val trimOptions: List<TrimOptionSimpleUiModel>,
    val exteriorColor: ArchiveFeedColorUiModel? = null,
    val interiorColor: ArchiveFeedColorUiModel? = null,
    val review: String? = null,
    val tags: List<String>? = null,
    val selectedOptions: List<ArchiveFeedSelectedOptionUiModel>
): java.io.Serializable

@Serializable
data class ArchiveFeedColorUiModel(
    val name: String,
    val colorImageUrl: String
): java.io.Serializable

@Serializable
data class ArchiveFeedSelectedOptionUiModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: Int,
    val subOptions: List<String>? = null,
    val tags: List<String>? = null
): java.io.Serializable
