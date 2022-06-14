package com.viniciuscoscia.catchacat.presenter.ui.model

import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.entity.CatImageCategory

sealed interface GalleryType {
    fun getDisplayName(): String

    object RandomImages : GalleryType {
        override fun getDisplayName() = "Random Images"
    }

    object RandomGifs : GalleryType {
        override fun getDisplayName() = "Random GIFs"
    }

    data class Breed(val breedModel: CatBreed) : GalleryType {
        override fun getDisplayName() =
            "Breed: ${breedModel.name.lowercase().replaceFirstChar { it.uppercase() }} >"
    }

    data class Category(val categoryUIModel: CatImageCategory) : GalleryType {
        override fun getDisplayName() =
            "Category: ${categoryUIModel.name}}"
    }
}
