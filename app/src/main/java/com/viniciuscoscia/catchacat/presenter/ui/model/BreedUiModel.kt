package com.viniciuscoscia.catchacat.presenter.ui.model

import com.viniciuscoscia.catchacat.domain.entity.CatBreed

data class BreedUiModel(val id: String, val name: String)

fun CatBreed.toUiModel() = BreedUiModel(
    id = id,
    name = name
)

fun List<CatBreed>.toUIModel() = map { it.toUiModel() }