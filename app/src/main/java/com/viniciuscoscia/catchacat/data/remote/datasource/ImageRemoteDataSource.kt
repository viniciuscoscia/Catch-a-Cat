package com.viniciuscoscia.catchacat.data.remote.datasource

import com.viniciuscoscia.catchacat.data.remote.entity.CatImagesRequestItem
import com.viniciuscoscia.catchacat.data.remote.entity.SearchParams
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ImageRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : ImageRemoteDataSource {
    override suspend fun getCatImagesByPage(
        page: Int,
        searchParams: SearchParams?
    ): List<CatImagesRequestItem> {
        return httpClient.get {
            url {
                path("$BASE_IMAGES_PATH/$SEARCH_PATH")
                searchParams?.forEach { requestParameter ->
                    encodedParameters.append(
                        requestParameter.key,
                        requestParameter.value.joinToString(",")
                    )
                }
                parameters.append(PAGE_PARAMETER, page.toString())
                parameters.append(PAGE_SIZE_PARAMETER, DEFAULT_PAGE_SIZE.toString())
            }
        }.body()
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 12
        private const val BASE_IMAGES_PATH = "images"
        private const val SEARCH_PATH = "search"
        private const val PAGE_SIZE_PARAMETER = "limit"
        private const val PAGE_PARAMETER = "page"
    }
}

interface ImageRemoteDataSource {
    suspend fun getCatImagesByPage(
        page: Int,
        searchParams: SearchParams? = null
    ): List<CatImagesRequestItem>
}