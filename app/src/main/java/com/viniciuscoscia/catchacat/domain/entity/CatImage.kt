package com.viniciuscoscia.catchacat.domain.entity

data class CatImage(
    val id: String,
    val url: String,
    val catBreed: CatBreed? = null,
    val categories: List<ImageCategory>? = null,
    val height: Int? = null,
    val width: Int? = null
)