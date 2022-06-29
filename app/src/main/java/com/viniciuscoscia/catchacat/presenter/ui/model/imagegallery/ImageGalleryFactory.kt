package com.viniciuscoscia.catchacat.presenter.ui.model.imagegallery

import androidx.paging.cachedIn
import com.viniciuscoscia.catchacat.data.paging.CatImagesSearchPager
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.params.MimeTypes
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
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
                    GalleryType.RandomImages -> {
                        ImageSearchParam.MimeType(
                            mimeTypes = listOf(
                                MimeTypes.JPG,
                                MimeTypes.PNG
                            )
                        )
                    }
                    GalleryType.RandomGifs -> {
                        ImageSearchParam.MimeType(
                            mimeTypes = listOf(
                                MimeTypes.GIF,
                            )
                        )
                    }
                    is GalleryType.Breed -> {
                        ImageSearchParam.BreedId(
                            breedId = galleryType.breedModel.id
                        )
                    }
                    is GalleryType.Category -> {
                        ImageSearchParam.CategoryIds(
                            category = galleryType.categoryUIModel.id.toInt()
                        )
                    }
                }
            ).apply {
                addAll(searchParams)
            }
        ).cachedIn(coroutineScope)
    )
}