package com.viniciuscoscia.catchacat.domain.entity

data class CatRandomImage(
    val id: String,
    val imageUrl: String,
    val catBreed: CatBreed? = null,
    val categories: List<ImageCategory>? = null
)