package com.viniciuscoscia.catchacat.domain.di

import com.viniciuscoscia.catchacat.domain.usecase.GetCatBreedsUseCase
import com.viniciuscoscia.catchacat.domain.usecase.GetCatBreedsUseCaseImpl
import com.viniciuscoscia.catchacat.domain.usecase.GetCatImagesUseCase
import com.viniciuscoscia.catchacat.domain.usecase.GetCatImagesUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetCatImagesUseCase> { GetCatImagesUseCaseImpl(catRepository = get()) }
    factory<GetCatBreedsUseCase> { GetCatBreedsUseCaseImpl(catRepository = get()) }
}