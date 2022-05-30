package com.viniciuscoscia.catchacat.data.di

import com.viniciuscoscia.catchacat.data.remote.datasource.RemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.datasource.RemoteDataSourceImpl
import com.viniciuscoscia.catchacat.data.remote.provideKtorHttpClient
import org.koin.dsl.module

val dataModule = module {
    single { provideKtorHttpClient() }
    single<RemoteDataSource> { RemoteDataSourceImpl(httpClient = get()) }
}