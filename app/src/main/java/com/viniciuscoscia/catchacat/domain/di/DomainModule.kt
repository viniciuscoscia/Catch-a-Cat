package com.viniciuscoscia.catchacat.domain.di

import com.viniciuscoscia.catchacat.domain.usecase.GetCatImagesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCatImagesUseCase(imageRepository = get()) }
}