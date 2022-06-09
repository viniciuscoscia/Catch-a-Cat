package com.viniciuscoscia.catchacat.data.repository

import com.viniciuscoscia.catchacat.data.remote.datasource.BreedRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.entity.toDomain
import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatRepositoryImpl(
    private val imageRemoteSource: ImageRemoteDataSource,
    private val breedRemoteSource: BreedRemoteDataSource
) : CatRepository {
    override suspend fun getCatImages(
        page: Int,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>> = withContext(Dispatchers.IO) {
        runCatching {
            imageRemoteSource.getCatImagesByPage(
                page = page,
                searchParams = hashMapOf<String, List<String>>()
                    .apply {
                        searchParams?.forEach { searchParam ->
                            putAll(searchParam.toMap())
                        }
                    }).toDomain()
        }
    }

    override suspend fun getCatBreeds(): Result<List<CatBreed>> {
        return runCatching {
            breedRemoteSource.getCatBreeds().toDomain()
        }
    }
}