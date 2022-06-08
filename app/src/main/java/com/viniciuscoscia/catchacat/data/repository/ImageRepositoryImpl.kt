package com.viniciuscoscia.catchacat.data.repository

import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.entity.toDomain
import com.viniciuscoscia.catchacat.domain.entity.CatRandomImage
import com.viniciuscoscia.catchacat.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(
    private val remoteSource: ImageRemoteDataSource
) : ImageRepository {
    override suspend fun fetchCatImages(page: Int): Result<List<CatRandomImage>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                remoteSource.getCatImagesByPage(page).toDomain()
            }
        }
    }
}