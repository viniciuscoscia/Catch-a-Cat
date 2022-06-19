package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.domain.usecase.GetCatBreedsUseCase
import com.viniciuscoscia.catchacat.domain.usecase.GetImageCategoriesUseCase
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
import com.viniciuscoscia.catchacat.presenter.ui.model.imagegallery.ImageGallery
import com.viniciuscoscia.catchacat.presenter.ui.model.imagegallery.ImageGalleryFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class GalleriesGenerator(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val getImageCategoriesUseCase: GetImageCategoriesUseCase,
    private val imagesSearchPager: CatImagesSearchPager
) {
    fun buildGalleries(coroutineScope: CoroutineScope): Flow<ImageGallery> {
        val galleryFactory = ImageGalleryFactory(imagesSearchPager, coroutineScope)

        return channelFlow {
            emitGalleries(galleryFactory)
        }
    }

    private suspend fun ProducerScope<ImageGallery>.emitGalleries(
        galleryFactory: ImageGalleryFactory,
    ) {
        emitDefaultGalleries(galleryFactory)
        emitOtherGalleries(galleryFactory)
    }

    private fun ProducerScope<ImageGallery>.emitOtherGalleries(
        galleryFactory: ImageGalleryFactory
    ) = launch {
        awaitAll(getCategoriesAsync(galleryFactory), getCatBreedsAsync(galleryFactory))
            .flatten()
            .shuffled()
            .forEach { imageGallery: ImageGallery ->
                send(imageGallery)
            }
    }

    private suspend fun ProducerScope<ImageGallery>.emitDefaultGalleries(
        galleryFactory: ImageGalleryFactory
    ) {
        defaultGalleries.forEach {
            send(galleryFactory.galleryTypeToImageGallery(it))
        }
    }

    private fun CoroutineScope.getCategoriesAsync(galleryFactory: ImageGalleryFactory): Deferred<List<ImageGallery>> =
        async(Dispatchers.IO) {
            getImageCategoriesUseCase()
                .getOrDefault(emptyList())
                .map {
                    galleryFactory.galleryTypeToImageGallery(GalleryType.Category(it))
                }
        }

    private fun CoroutineScope.getCatBreedsAsync(galleryFactory: ImageGalleryFactory): Deferred<List<ImageGallery>> =
        async(Dispatchers.IO) {
            getCatBreedsUseCase()
                .getOrDefault(emptyList())
                .map {
                    galleryFactory.galleryTypeToImageGallery(GalleryType.Breed(it))
                }
        }

    companion object {
        private val defaultGalleries = listOf(
            GalleryType.RandomImages,
            GalleryType.RandomGifs
        )
    }
}