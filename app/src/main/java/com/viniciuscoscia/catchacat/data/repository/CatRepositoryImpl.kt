package com.viniciuscoscia.catchacat.data.repository

import com.viniciuscoscia.catchacat.data.remote.datasource.BreedRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.datasource.CategoriesRemoteSource
import com.viniciuscoscia.catchacat.data.remote.datasource.ImageRemoteDataSource
import com.viniciuscoscia.catchacat.data.remote.entity.toDomain
import com.viniciuscoscia.catchacat.domain.entity.Breed
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.CatImageCategory
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatRepositoryImpl(
    private val imageRemoteSource: ImageRemoteDataSource,
    private val breedRemoteSource: BreedRemoteDataSource,
    private val categoriesRemoteSource: CategoriesRemoteSource
) : CatRepository {
    override suspend fun getImages(
        page: Int,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>> = withContext(Dispatchers.IO) {
        runCatching {
            imageRemoteSource.getCatImagesByPage(
                page = page,
                searchParams = hashMapOf<String, List<String>>()
                    .apply {
                        searchParams?.forEach {
                            putAll(it.toMap())
                        }
                    }).toDomain()
        }
    }

    override suspend fun getBreeds(): Result<List<Breed>> {
        return runCatching {
            breedRemoteSource.getCatBreeds().toDomain().filterNotNull()
        }
    }

    override suspend fun getImageCategories(): Result<List<CatImageCategory>> {
        return runCatching {
            categoriesRemoteSource.getImageCategories().toDomain()
        }
    }
}