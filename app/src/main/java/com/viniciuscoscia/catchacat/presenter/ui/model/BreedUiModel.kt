package com.viniciuscoscia.catchacat.presenter.ui.model

import com.viniciuscoscia.catchacat.domain.entity.Breed

data class BreedUiModel(val id: String, val name: String)

fun Breed.toUiModel() = BreedUiModel(
    id = id,
    name = name
)

fun List<Breed>.toUIModel() = map { it.toUiModel() }