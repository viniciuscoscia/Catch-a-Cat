package com.viniciuscoscia.catchacat.data.remote.entity

import com.viniciuscoscia.catchacat.domain.entity.CatImage
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponseItem(
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)

fun ImageResponseItem.toDomain() = CatImage(
    height = height,
    id = id,
    url = url,
    width = width
)