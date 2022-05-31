package com.viniciuscoscia.catchacat.data.repository

import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.entity.toDomain
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val remoteSource: ImageRemoteDataSource
) : ImageRepository {
    override suspend fun fetchCatImages(page: Int): Result<List<CatImage>> {
        return runCatching {
            remoteSource.getCatImagesByPage(page).toDomain()
        }
    }
}