package com.viniciuscoscia.catchacat.domain.usecase

import com.viniciuscoscia.catchacat.domain.entity.CatBreed
import com.viniciuscoscia.catchacat.domain.repository.CatRepository

class GetCatBreedsUseCaseImpl(private val catRepository: CatRepository) : GetCatBreedsUseCase {
    override suspend operator fun invoke() = catRepository.getCatBreeds()
}

interface GetCatBreedsUseCase {
    suspend operator fun invoke(): Result<List<CatBreed>>
}