package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.catchacat.domain.usecase.GetCatImagesUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class CatImagesViewModel(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            val a = getCatImagesUseCase(0)

            if (a.isSuccess) {
                a.getOrNull()?.forEach {
                    Timber.d(it.imageUrl)
                }
            }
        }
    }
}