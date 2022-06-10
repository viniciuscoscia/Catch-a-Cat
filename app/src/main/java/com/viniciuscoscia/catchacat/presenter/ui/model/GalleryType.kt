package com.viniciuscoscia.catchacat.presenter.ui.model

sealed class GalleryType(val displayName: String) {
    object RandomImages : GalleryType("Random Images")
    object RandomGifs : GalleryType("Random GIFs")
    class Breed(val breedModel: CatBreedUiModel) : GalleryType("Breed")
    class Category(val categoryModel: List<CATegoryModel>) : GalleryType("CATegory")
}
