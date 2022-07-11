package com.viniciuscoscia.catchacat.domain.repository

import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.ImageCategory
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam

interface CatRepository {
    suspend fun getImages(
        page: Int = 0,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>>

    suspend fun getBreeds(): Result<List<CatBreed>>
    suspend fun getImageCategories(): Result<List<ImageCategory>>
}