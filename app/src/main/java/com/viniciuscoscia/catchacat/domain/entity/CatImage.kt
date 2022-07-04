package com.viniciuscoscia.catchacat.domain.entity

data class CatImage(
    val id: String,
    val url: String,
    val breed: Breed? = null,
    val categories: List<CatImageCategory>? = null,
    val height: Int? = null,
    val width: Int? = null
)