package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.lifecycle.ViewModel
import com.viniciuscoscia.catchacat.domain.usecase.GetCatImagesUseCase

class CatImagesViewModel(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : ViewModel() {

}