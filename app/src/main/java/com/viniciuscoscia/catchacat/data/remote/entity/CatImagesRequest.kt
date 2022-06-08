package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.CatRandomImage
import kotlinx.serialization.Serializable

@Serializable
data class CatImagesRequestItem(
    val id: String,
    val breeds: List<BreedResponseItem>? = null,
    val categories: List<CategoryResponseItem>? = null,
    val height: Int?,
    val url: String,
    val width: Int?
)

fun CatImagesRequestItem.toDomain() = CatRandomImage(
    id = id,
    imageUrl = url,
    catBreed = breeds?.firstOrNull()?.toDomain(),
    categories = categories?.map { it.toDomain() }
)

fun List<CatImagesRequestItem>.toDomain() = map { it.toDomain() }