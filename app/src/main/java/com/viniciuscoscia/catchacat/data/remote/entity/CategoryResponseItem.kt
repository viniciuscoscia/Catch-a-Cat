package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.ImageCATegory
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponseItem(
    val id: Int,
    val name: String
)

fun CategoryResponseItem.toDomain() = ImageCATegory(
    id = id,
    name = name
)