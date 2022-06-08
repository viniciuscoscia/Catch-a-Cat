package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.ImageCategory
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponseItem(
    val id: Int,
    val name: String
)

fun CategoryResponseItem.toDomain() = ImageCategory(
    id = id,
    name = name
)