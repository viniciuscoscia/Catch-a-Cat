package com.viniciuscoscia.catchacat.data.repository

import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.entity.toDomain
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val remoteSource: ImageRemoteDataSource
) : ImageRepository {
    override suspend fun fetchCatImages(
        page: Int,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>> = withContext(Dispatchers.IO) {
        runCatching {
            remoteSource.getCatImagesByPage(
                page = page,
                searchParams = hashMapOf<String, List<String>>()
                    .apply {
                        searchParams?.forEach { searchParam ->
                            putAll(searchParam.toMap())
                        }
                    }).toDomain()
        }
    }
}