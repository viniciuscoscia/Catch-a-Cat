package com.viniciuscoscia.catchacat.presenter.ui.model

import com.viniciuscoscia.catchacat.domain.entity.CatImageCategory

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

    data class Breed(val breedModel: com.viniciuscoscia.catchacat.domain.entity.Breed) :
        GalleryType {
        override fun getDisplayName() =
            "Breed: ${breedModel.name.lowercase().replaceFirstChar { it.uppercase() }} >"

        override fun getId(): String = breedModel.id
    }

    data class Category(val categoryUIModel: CatImageCategory) : GalleryType {
        override fun getDisplayName() =
            "Category: ${categoryUIModel.name}}"

        override fun getId(): String = categoryUIModel.id
    }
}
