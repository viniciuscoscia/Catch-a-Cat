package com.viniciuscoscia.catchacat.domain.repository

import com.viniciuscoscia.catchacat.domain.entity.Breed
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.CatImageCategory
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam

interface CatRepository {
    suspend fun getImages(
        page: Int = 0,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>>

    suspend fun getBreeds(): Result<List<Breed>>
    suspend fun getImageCategories(): Result<List<CatImageCategory>>
}