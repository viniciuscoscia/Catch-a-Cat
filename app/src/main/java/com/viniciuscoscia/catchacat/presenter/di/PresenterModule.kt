@file:Suppress("RemoveExplicitTypeArguments")

package com.viniciuscoscia.catchacat.presenter.di

import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.CatGalleriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel {
        CatGalleriesViewModel(
            imagesSearchPager = get(),
            getCatBreedsUseCase = get()
        )
    }
    factory<CatImagesSearchPager> { CatImagesSearchPager(catRepository = get()) }
}