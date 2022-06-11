package com.viniciuscoscia.catchacat.data.remote.datasource

import com.viniciuscoscia.catchacat.data.remote.entity.CategoryResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CategoriesRemoteSourceImpl(
    private val httpClient: HttpClient,
) : CategoriesRemoteSource {
    override suspend fun getImageCategories(): List<CategoryResponseItem> {
        return httpClient.get {
            url {
                path(BASE_CATEGORIES_PATH)
            }
        }.body()
    }

    companion object {
        private const val BASE_CATEGORIES_PATH = "categories"
    }
}

interface CategoriesRemoteSource {
    suspend fun getImageCategories(): List<CategoryResponseItem>
}