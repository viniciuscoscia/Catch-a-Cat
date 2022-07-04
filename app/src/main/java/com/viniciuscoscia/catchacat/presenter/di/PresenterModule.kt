@file:Suppress("RemoveExplicitTypeArguments")

package com.viniciuscoscia.catchacat.presenter.di

import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.GalleriesGenerator
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.ImageGalleriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel {
        ImageGalleriesViewModel(
            galleriesGenerator = GalleriesGenerator(
                getBreedsUseCase = get(),
                getImageCategoriesUseCase = get(),
                imagesSearchPager = get()
            )
        )
    }
    factory<CatImagesSearchPager> { CatImagesSearchPager(catRepository = get()) }
}