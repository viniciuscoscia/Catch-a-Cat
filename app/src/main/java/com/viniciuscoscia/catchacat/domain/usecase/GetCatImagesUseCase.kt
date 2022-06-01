package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.repository.ImageRepository

class GetCatImagesUseCaseImpl(private val imageRepository: ImageRepository) : GetCatImagesUseCase {
    override suspend operator fun invoke(page: Int): Result<List<CatImage>> {
        return imageRepository.fetchCatImages(page)
    }
}

interface GetCatImagesUseCase {
    suspend operator fun invoke(page: Int): Result<List<CatImage>>
}