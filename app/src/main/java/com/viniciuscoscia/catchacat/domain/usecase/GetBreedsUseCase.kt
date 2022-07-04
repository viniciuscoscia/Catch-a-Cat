package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.Breed
import com.viniciuscoscia.catchacat.domain.repository.CatRepository

class GetBreedsUseCaseImpl(private val catRepository: CatRepository) : GetBreedsUseCase {
    override suspend operator fun invoke() = catRepository.getBreeds()
}

interface GetBreedsUseCase {
    suspend operator fun invoke(): Result<List<Breed>>
}