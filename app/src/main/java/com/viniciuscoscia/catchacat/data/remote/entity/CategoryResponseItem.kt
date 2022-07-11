package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.ImageCategory
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponseItem(
    val id: Int,
    val name: String
)

fun List<CategoryResponseItem>.toDomain() = map { it.toDomain() }

fun CategoryResponseItem.toDomain() = ImageCategory(
    id = id.toString(),
    name = name
)