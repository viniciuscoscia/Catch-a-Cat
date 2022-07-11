package com.viniciuscoscia.catchacat.presenter.ui.model

import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.entity.ImageCategory

sealed interface GalleryType {
    fun getDisplayName(): String
    fun getId(): String?

    object RandomImages : GalleryType {
        override fun getDisplayName() = "Random Images"
        override fun getId(): String? = null
    }

    object RandomGifs : GalleryType {
        override fun getDisplayName() = "Random GIFs"
        override fun getId(): String? = null
    }

    data class Breed(val catBreed: CatBreed) :
        GalleryType {
        override fun getDisplayName() =
            "Breed: ${catBreed.name.lowercase().replaceFirstChar { it.uppercase() }} >"

        override fun getId(): String = catBreed.id
    }

    data class Category(val imageCategory: ImageCategory) : GalleryType {
        override fun getDisplayName() =
            "Category: ${imageCategory.name} >"

        override fun getId(): String = imageCategory.id
    }
}
