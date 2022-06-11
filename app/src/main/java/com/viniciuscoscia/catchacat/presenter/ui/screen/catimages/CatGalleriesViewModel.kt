package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.entity.ImageCATegory
import com.viniciuscoscia.catchacat.domain.usecase.GetCatBreedsUseCase
import com.viniciuscoscia.catchacat.domain.usecase.GetImageCategoriesUseCase
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
import com.viniciuscoscia.catchacat.presenter.ui.model.ImageGallery
import com.viniciuscoscia.catchacat.presenter.ui.model.factory.ImageGalleryFactory
import kotlinx.coroutines.launch

class CatGalleriesViewModel(
    imagesSearchPager: CatImagesSearchPager,
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val getImageCategoriesUseCase: GetImageCategoriesUseCase
) : ViewModel() {
    private val galleryFactory = ImageGalleryFactory(imagesSearchPager, viewModelScope)

    private val _imageGalleries = mutableStateListOf<ImageGallery>()
    val imageGalleries: List<ImageGallery> = _imageGalleries

    private var catBreeds: List<CatBreed> = listOf()
    private var imageCategories: List<ImageCATegory> = listOf()

    init {
        addDefaultImageGalleries()
        viewModelScope.launch {
            catBreeds = getCatBreedsUseCase().getOrDefault(emptyList())
            imageCategories = getImageCategoriesUseCase().getOrDefault(emptyList())
        }
    }

    private fun addDefaultImageGalleries() {
        defaultGalleries.forEach {
            _imageGalleries.add(galleryFactory.galleryTypeToImageGallery(it))
        }
    }

    sealed interface ScreenEvents {
        data class ShowSnackbar(val message: String) : ScreenEvents
        data class Navigate(val route: String) : ScreenEvents
    }

    companion object {
        private val defaultGalleries = listOf(
            GalleryType.RandomImages,
            GalleryType.RandomGifs
        )
    }
}