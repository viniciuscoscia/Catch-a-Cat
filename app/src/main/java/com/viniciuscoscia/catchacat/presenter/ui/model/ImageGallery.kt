package com.viniciuscoscia.catchacat.presenter.ui.model

import androidx.paging.PagingData
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import kotlinx.coroutines.flow.Flow

data class ImageGallery(
    val title: String,
    val images: Flow<PagingData<CatImage>>
)
