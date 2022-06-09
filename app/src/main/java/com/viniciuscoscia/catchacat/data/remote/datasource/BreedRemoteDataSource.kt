package com.viniciuscoscia.catchacat.data.remote.datasource

import com.viniciuscoscia.catchacat.data.remote.entity.BreedResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class BreedRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : BreedRemoteDataSource {
    override suspend fun getCatBreeds(): List<BreedResponseItem> {
        return httpClient.get {
            url {
                path(BASE_BREEDS_PATH)
            }
        }.body()
    }

    companion object {
        private const val BASE_BREEDS_PATH = "breeds"
    }
}

interface BreedRemoteDataSource {
    suspend fun getCatBreeds(): List<BreedResponseItem>
}