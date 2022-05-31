package com.viniciuscoscia.catchacat.domain.entity

data class CatImage(
    val id: String,
    val imageUrl: String,
    val breedName: String?,
    val breedId: String?
)
