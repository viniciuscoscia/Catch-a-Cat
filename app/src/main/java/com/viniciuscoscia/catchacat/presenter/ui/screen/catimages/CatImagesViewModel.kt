package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.viniciuscoscia.catchacat.presenter.paging.CatImagesPager

class CatImagesViewModel(
    catImagesPager: CatImagesPager
) : ViewModel() {
    val catImages = catImagesPager.getCatImagesFlow().cachedIn(viewModelScope)
}