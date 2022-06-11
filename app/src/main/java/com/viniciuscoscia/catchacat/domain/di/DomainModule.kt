package com.viniciuscoscia.catchacat.domain.di

import com.viniciuscoscia.catchacat.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<GetCatImagesUseCase> { GetCatImagesUseCaseImpl(catRepository = get()) }
    factory<GetCatBreedsUseCase> { GetCatBreedsUseCaseImpl(catRepository = get()) }
    factory<GetImageCategoriesUseCase> { GetImageCategoriesUseCaseImpl(catRepository = get()) }
}