package com.softeer.data.model

import com.google.gson.annotations.SerializedName

data class ArchivingDto(
    @SerializedName("nextOffset")
    val nextOffset: Int,
    @SerializedName("archivings")
    val carFeeds: List<ArchivingFeedDto>
)

data class ArchivingFeedDto(
    @SerializedName("feedId")
    val id: String,
    @SerializedName("modelName")
    val modelName: String,
    @SerializedName("creationDate")
    val createdDate: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("trim")
    val trim: ArchivingModelDto,
    @SerializedName("engine")
    val engine: ArchivingTrimOptionDto,
    @SerializedName("bodyType")
    val bodyType: ArchivingTrimOptionDto,
    @SerializedName("wheelDrive")
    val wheelDrive: ArchivingTrimOptionDto,
    @SerializedName("interiorColor")
    val interiorColor: ArchivingInteriorDto,
    @SerializedName("exteriorColor")
    val exteriorColor: ArchivingExteriorDto,
    @SerializedName("selectedOptions")
    val selectedOptions: List<ArchivingSelectedDto>,
    @SerializedName("purchase")
    val purchased: Boolean
)

data class ArchivingDetailsDto(
    @SerializedName("feedId")
    val id: String,
    @SerializedName("modelName")
    val modelName: String,
    @SerializedName("creationDate")
    val createdDate: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("trim")
    val trim: ArchivingModelDto,
    @SerializedName("engine")
    val engine: ArchivingTrimOptionDto,
    @SerializedName("bodyType")
    val bodyType: ArchivingTrimOptionDto,
    @SerializedName("wheelDrive")
    val wheelDrive: ArchivingTrimOptionDto,
    @SerializedName("interiorColor")
    val interiorColor: ArchivingInteriorDto,
    @SerializedName("exteriorColor")
    val exteriorColor: ArchivingExteriorDto,
    @SerializedName("selectedOptions")
    val selectedOptions: List<ArchivingSelectedDto>,
    @SerializedName("purchase")
    val purchased: Boolean
)

data class ArchivingModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)

data class ArchivingTrimOptionDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("additionalPrice")
    val price: Int
)

data class ArchivingInteriorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String
)

data class ArchivingExteriorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("carImageUrl")
    val carImageUrl: String,
    @SerializedName("colorImageUrl")
    val colorImageUrl: String,
    @SerializedName("additionalPrice")
    val price: Int
)

data class ArchivingSelectedDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("review")
    val reviewText: String,
    @SerializedName("additionalPrice")
    val price: Int,
    @SerializedName("subOptions")
    val subOptions: List<String>,
    @SerializedName("tags")
    val tags: List<String>
)

data class SelectOptionsDto(
    @SerializedName("selectOptions")
    val selectOptions: List<ArchivingSelectOptionDto>
)

data class ArchivingSelectOptionDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
)