package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.catchacat.presenter.navigation.Screen
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
import com.viniciuscoscia.catchacat.presenter.ui.model.UIEvents
import com.viniciuscoscia.catchacat.presenter.ui.model.imagegallery.ImageGallery
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ImageGalleriesViewModel(
    private val galleriesGenerator: GalleriesGenerator,
) : ViewModel() {
    private val _imageGalleries = mutableStateListOf<ImageGallery>()
    val imageGalleries: List<ImageGallery> = _imageGalleries

    private val _uiEvents = Channel<UIEvents>()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            galleriesGenerator.buildGalleries(viewModelScope).collect {
                _imageGalleries.add(it)
            }
        }
    }

    fun onGalleryTitleClicked(galleryType: GalleryType) {
        when (galleryType) {
            is GalleryType.Breed -> {
                _uiEvents.trySend(UIEvents.Navigate(Screen.BreedDetails(breedId = galleryType.getId())))
            }
            else -> {
                _uiEvents.trySend(UIEvents.ShowSnackbar("Batata doce"))
                Timber.d("Clicked on title for gallery type: ${galleryType.getDisplayName()}")
            }
        }
    }
}