package com.viniciuscoscia.catchacat.data.remote.datasource

import io.ktor.client.*

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {

}

interface RemoteDataSource {

}