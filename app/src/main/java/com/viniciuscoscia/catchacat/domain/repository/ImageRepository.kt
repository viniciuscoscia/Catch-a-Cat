package com.viniciuscoscia.catchacat.domain.repository

import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam

interface ImageRepository {
    suspend fun fetchCatImages(
        page: Int,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>>
}