package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.CatImageCategory
import com.viniciuscoscia.catchacat.domain.repository.CatRepository
import timber.log.Timber

class GetImageCategoriesUseCaseImpl(
    private val catRepository: CatRepository
) : GetImageCategoriesUseCase {
    override suspend operator fun invoke() = catRepository.getImageCategories().onFailure {
        Timber.e("Error fetching Image Categories", it)
    }
}

interface GetImageCategoriesUseCase {
    suspend operator fun invoke(): Result<List<CatImageCategory>>
}