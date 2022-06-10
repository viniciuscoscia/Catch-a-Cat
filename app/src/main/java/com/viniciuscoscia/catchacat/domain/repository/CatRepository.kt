package com.viniciuscoscia.catchacat.domain.repository

import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam

interface CatRepository {
    suspend fun getCatImages(
        page: Int = 0,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>>

    suspend fun getCatBreeds(): Result<List<CatBreed>>
}