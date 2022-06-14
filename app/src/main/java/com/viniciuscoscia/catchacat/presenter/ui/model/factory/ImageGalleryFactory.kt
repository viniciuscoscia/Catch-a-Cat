package com.viniciuscoscia.catchacat.presenter.ui.model.factory

import androidx.paging.cachedIn
import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.params.MimeTypes
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType.*
import com.viniciuscoscia.catchacat.presenter.ui.model.ImageGallery
import com.viniciuscoscia.catchacat.presenter.util.addAll
import kotlinx.coroutines.CoroutineScope

class ImageGalleryFactory(
    private val imagesSearchPager: CatImagesSearchPager,
    private val coroutineScope: CoroutineScope
) {
    fun galleryTypeToImageGallery(
        galleryType: GalleryType,
        searchParams: List<ImageSearchParam>? = null
    ) = ImageGallery(
        galleryType = galleryType,
        images = imagesSearchPager.searchForPagingImages(
            searchParams = arrayListOf(
                when (galleryType) {
                    is RandomImages -> {
                        ImageSearchParam.MimeType(
                            mimeTypes = listOf(
                                MimeTypes.JPG,
                                MimeTypes.PNG
                            )
                        )
                    }
                    is RandomGifs -> {
                        ImageSearchParam.MimeType(
                            mimeTypes = listOf(
                                MimeTypes.GIF,
                            )
                        )
                    }
                    is Breed -> {
                        ImageSearchParam.BreedId(
                            breedId = galleryType.breedModel.id
                        )
                    }
                    is Category -> {
                        ImageSearchParam.CategoryIds(
                            category = galleryType.categoryUIModel.id
                        )
                    }
                }
            ).apply {
                addAll(searchParams)
            }
        ).cachedIn(coroutineScope)
    )
}