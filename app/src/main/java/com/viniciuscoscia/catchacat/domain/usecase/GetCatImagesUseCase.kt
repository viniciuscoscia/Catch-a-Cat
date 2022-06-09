package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.repository.CatRepository

class GetCatImagesUseCaseImpl(private val catRepository: CatRepository) : GetCatImagesUseCase {
    override suspend operator fun invoke(
        page: Int,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>> {
        return catRepository.getCatImages(page, searchParams)
    }
}

interface GetCatImagesUseCase {
    suspend operator fun invoke(
        page: Int,
        searchParams: List<ImageSearchParam>? = null
    ): Result<List<CatImage>>
}