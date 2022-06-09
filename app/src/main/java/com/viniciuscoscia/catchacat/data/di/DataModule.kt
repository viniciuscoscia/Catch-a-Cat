@file:Suppress("RemoveExplicitTypeArguments")

package com.viniciuscoscia.catchacat.data.di

import com.viniciuscoscia.catchacat.data.remote.datasource.BreedRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.datasource.BreedRemoteDataSourceImpl
import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSourceImpl
import com.viniciuscoscia.catchacat.data.remote.provideKtorHttpClient
import com.viniciuscoscia.catchacat.data.repository.CatRepositoryImpl
import com.viniciuscoscia.catchacat.domain.repository.CatRepository
import io.ktor.client.*
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> { provideKtorHttpClient() }

    // Data Sources
    single<ImageRemoteDataSource> { ImageRemoteDataSourceImpl(httpClient = get()) }
    single<BreedRemoteDataSource> { BreedRemoteDataSourceImpl(httpClient = get()) }

    // Repository
    single<CatRepository> {
        CatRepositoryImpl(
            breedRemoteSource = get(),
            imageRemoteSource = get()
        )
    }
}