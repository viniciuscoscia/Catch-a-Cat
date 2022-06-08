package com.viniciuscoscia.catchacat.domain.repository

import com.viniciuscoscia.catchacat.domain.entity.CatRandomImage

interface ImageRepository {
    suspend fun fetchCatImages(page: Int): Result<List<CatRandomImage>>
}