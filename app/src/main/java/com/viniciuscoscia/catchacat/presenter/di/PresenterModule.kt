@file:Suppress("RemoveExplicitTypeArguments")

package com.viniciuscoscia.catchacat.presenter.di

import com.viniciuscoscia.catchacat.presenter.paging.CatImagesPager
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.CatImagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {
    viewModel {
        CatImagesViewModel(catImagesPager = get())
    }
    factory<CatImagesPager> { CatImagesPager(getCatImagesUseCase = get()) }
}