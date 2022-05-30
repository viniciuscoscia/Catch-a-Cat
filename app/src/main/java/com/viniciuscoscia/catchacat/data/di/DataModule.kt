package com.viniciuscoscia.catchacat.data.di

import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSourceImpl
import com.viniciuscoscia.catchacat.data.remote.provideKtorHttpClient
import org.koin.dsl.module

val dataModule = module {
    single { provideKtorHttpClient() }
    single<ImageRemoteDataSource> { ImageRemoteDataSourceImpl(httpClient = get()) }
}