package com.viniciuscoscia.catchacat.domain.repository

import com.viniciuscoscia.catchacat.domain.entity.CatImage

interface ImageRepository {
    suspend fun fetchCatImages(page: Int): Result<List<CatImage>>
}