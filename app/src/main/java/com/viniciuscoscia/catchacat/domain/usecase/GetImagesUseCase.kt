package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.repository.CatRepository
import timber.log.Timber

class GetImagesUseCaseImpl(private val catRepository: CatRepository) : GetImagesUseCase {
    override suspend operator fun invoke(
        page: Int,
        searchParams: List<ImageSearchParam>?
    ): Result<List<CatImage>> {
        return catRepository.getImages(page, searchParams)
            .onFailure {
                Timber.e("Error when getting cat images", it)
            }
    }
}

interface GetImagesUseCase {
    suspend operator fun invoke(
        page: Int,
        searchParams: List<ImageSearchParam>? = null
    ): Result<List<CatImage>>
}