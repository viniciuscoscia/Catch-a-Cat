package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.viniciuscoscia.catchacat.presenter.paging.CatImagesPager
import com.viniciuscoscia.catchacat.presenter.ui.model.ImageGallery
import kotlinx.coroutines.launch

class CatGalleriesViewModel(
    catImagesPager: CatImagesPager
) : ViewModel() {
    private val _imageGalleries = mutableStateListOf<ImageGallery>()
    val imageGalleries: List<ImageGallery> = _imageGalleries

    init {
        viewModelScope.launch {
            _imageGalleries.add(
                ImageGallery(
                    "Teste 1", catImagesPager.getCatImagesFlow().cachedIn(viewModelScope)
                )
            )
            _imageGalleries.add(
                ImageGallery(
                    "Teste 2", catImagesPager.getCatImagesFlow().cachedIn(viewModelScope)
                )
            )
        }
    }

    sealed interface ScreenEvents {
        data class ShowSnackbar(val message: String) : ScreenEvents
        data class Navigate(val route: String) : ScreenEvents
    }
}