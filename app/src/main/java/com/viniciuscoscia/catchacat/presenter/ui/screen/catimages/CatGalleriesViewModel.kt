package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.presenter.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.presenter.ui.model.ImageGallery
import kotlinx.coroutines.launch

class CatGalleriesViewModel(
    catImagesSearchPager: CatImagesSearchPager
) : ViewModel() {
    private val _imageGalleries = mutableStateListOf<ImageGallery>()
    val imageGalleries: List<ImageGallery> = _imageGalleries

    init {
        viewModelScope.launch {
            _imageGalleries.add(
                ImageGallery(
                    "Teste 1", catImagesSearchPager
                        .searchForPagingImages()
                        .cachedIn(viewModelScope)
                )
            )

            _imageGalleries.add(
                ImageGallery(
                    "Teste 2", catImagesSearchPager.searchForPagingImages(
                        searchParams = listOf(ImageSearchParam.BreedId("beng"))
                    ).cachedIn(viewModelScope)
                )
            )
        }
    }

    sealed interface ScreenEvents {
        data class ShowSnackbar(val message: String) : ScreenEvents
        data class Navigate(val route: String) : ScreenEvents
    }
}