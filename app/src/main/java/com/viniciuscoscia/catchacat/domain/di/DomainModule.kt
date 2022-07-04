package com.viniciuscoscia.catchacat.domain.di

import com.viniciuscoscia.catchacat.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<GetImagesUseCase> { GetImagesUseCaseImpl(catRepository = get()) }
    factory<GetBreedsUseCase> { GetBreedsUseCaseImpl(catRepository = get()) }
    factory<GetImageCategoriesUseCase> { GetImageCategoriesUseCaseImpl(catRepository = get()) }
}