package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.CatImage
import kotlinx.serialization.Serializable

@Serializable
data class CatImagesRequestItem(
    val breeds: List<BreedResponseItem>,
    val categories: List<Category>? = null,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) {
    @Serializable
    data class Category(
        val id: Int,
        val name: String
    )
}

fun CatImagesRequestItem.toDomain() = CatImage(
    id = id,
    imageUrl = url,
    breedName = breeds.firstOrNull()?.name,
    breedId = breeds.firstOrNull()?.id,
)

fun List<CatImagesRequestItem>.toDomain() = map { it.toDomain() }