package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.domain.usecase.GetCatBreedsUseCase
import com.viniciuscoscia.catchacat.domain.usecase.GetImageCategoriesUseCase
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
import com.viniciuscoscia.catchacat.presenter.ui.model.ImageGallery
import com.viniciuscoscia.catchacat.presenter.ui.model.factory.ImageGalleryFactory
import kotlinx.coroutines.*
import timber.log.Timber

class CatGalleriesViewModel(
    imagesSearchPager: CatImagesSearchPager,
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val getImageCategoriesUseCase: GetImageCategoriesUseCase
) : ViewModel() {
    private val galleryFactory = ImageGalleryFactory(imagesSearchPager, viewModelScope)

    private val _imageGalleries = mutableStateListOf<ImageGallery>()
    val imageGalleries: List<ImageGallery> = _imageGalleries

    init {
        addDefaultImageGalleries()
        fetchGalleries()
    }

    private fun fetchGalleries() = viewModelScope.launch(Dispatchers.IO) {
        awaitAll(getCatBreedsAsync(), getCategoriesAsync())
            .flatten()
            .shuffled()
            .forEach {
                Timber.d("Added gallery: $it")
                _imageGalleries.add(it)
            }.also {
                // TODO Hide Loading
            }
    }

    private fun CoroutineScope.getCategoriesAsync(): Deferred<List<ImageGallery>> = async {
        getImageCategoriesUseCase()
            .getOrDefault(emptyList())
            .map {
                galleryFactory.galleryTypeToImageGallery(GalleryType.Category(it))
            }
    }

    private fun CoroutineScope.getCatBreedsAsync(): Deferred<List<ImageGallery>> = async {
        getCatBreedsUseCase()
            .getOrDefault(emptyList())
            .map {
                galleryFactory.galleryTypeToImageGallery(GalleryType.Breed(it))
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