package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.catchacat.presenter.ui.model.imagegallery.ImageGallery
import kotlinx.coroutines.launch

class CatGalleriesViewModel(
    private val galleriesGenerator: GalleriesGenerator,
) : ViewModel() {
    private val _imageGalleries = mutableStateListOf<ImageGallery>()
    val imageGalleries: List<ImageGallery> = _imageGalleries

    init {
        viewModelScope.launch {
            galleriesGenerator.buildGalleries(viewModelScope).collect {
                _imageGalleries.add(it)
            }
        }
    }

    sealed interface ScreenEvents {
        data class ShowSnackbar(val message: String) : ScreenEvents
        data class Navigate(val route: String) : ScreenEvents
    }
}