package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCatImagesUseCase(private val imageRepository: ImageRepository) {
    suspend operator fun invoke(page: Int) = withContext(Dispatchers.IO) {
        imageRepository.fetchCatImages(page)
    }
}