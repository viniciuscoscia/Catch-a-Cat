package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.CatImageCategory
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponseItem(
    val id: Int,
    val name: String
)

fun List<CategoryResponseItem>.toDomain() = map { it.toDomain() }

fun CategoryResponseItem.toDomain() = CatImageCategory(
    id = id,
    name = name
)