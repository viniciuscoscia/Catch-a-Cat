package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.ImageCATegory
import com.viniciuscoscia.catchacat.domain.repository.CatRepository

class GetImageCategoriesUseCaseImpl(
    private val catRepository: CatRepository
) : GetImageCategoriesUseCase {
    override suspend operator fun invoke() = catRepository.getImageCategories()
}

interface GetImageCategoriesUseCase {
    suspend operator fun invoke(): Result<List<ImageCATegory>>
}