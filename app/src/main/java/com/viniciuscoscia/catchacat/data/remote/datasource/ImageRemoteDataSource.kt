package com.viniciuscoscia.catchacat.data.remote.datasource

import com.viniciuscoscia.catchacat.data.remote.entity.BreedResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ImageRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : ImageRemoteDataSource {
    override suspend fun getCatImagesByPage(page: Int): List<BreedResponseItem> {
        return httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                path("images")
                parameters.append(PAGE_SIZE_PARAMETER, DEFAULT_PAGE_SIZE.toString())
            }
        }.body()
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
        private const val PAGE_SIZE_PARAMETER = "limit"
    }
}

interface ImageRemoteDataSource {
    suspend fun getCatImagesByPage(page: Int): List<BreedResponseItem>
}