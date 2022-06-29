@file:OptIn(ExperimentalPagerApi::class)

package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.presenter.ui.component.TextFields
import com.viniciuscoscia.catchacat.presenter.ui.component.image.CatLoader
import com.viniciuscoscia.catchacat.presenter.ui.component.loading.LoadingBox
import com.viniciuscoscia.catchacat.presenter.ui.model.GalleryType
import com.viniciuscoscia.catchacat.presenter.ui.model.imagegallery.ImageGallery
import com.viniciuscoscia.catchacat.presenter.ui.theme.CatchACatTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun GalleriesScreen(
    navController: NavHostController,
    viewModel: ImageGalleriesViewModel = getViewModel()
) {
    CatchACatTheme {
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                val imageGalleries: List<ImageGallery> = viewModel.imageGalleries

                if (imageGalleries.isEmpty()) {
                    LoadingBox(modifier = Modifier.fillMaxSize())
                    return@Column
                }

                GalleriesVerticalPager(
                    imageGalleries = imageGalleries,
                    onTitleClicked = {
                        viewModel.onGalleryTitleClicked(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun GalleriesVerticalPager(
    imageGalleries: List<ImageGallery>,
    onTitleClicked: ((GalleryType) -> Unit)
) {
    val pagerState = rememberPagerState()

    VerticalPager(
        count = imageGalleries.size,
        state = pagerState,
    ) { index ->
        imageGalleries[index].apply {
            ImageCarousel(
                galleryType = galleryType,
                images = images.collectAsLazyPagingItems(),
                onTitleClicked = { onTitleClicked.invoke(galleryType) }
            )
        }
    }
}

@Composable
private fun ImageCarousel(
    galleryType: GalleryType,
    images: LazyPagingItems<CatImage>,
    onImageClicked: (() -> Unit)? = null,
    onTitleClicked: (() -> Unit)? = null
) {
    val pagerState = rememberPagerState()
    val updatedOnImageClicked by rememberUpdatedState(newValue = onImageClicked)
    val updatedOnTitleClicked by rememberUpdatedState(newValue = onTitleClicked)

    Column(modifier = Modifier.fillMaxSize()) {
        TextFields.Title(
            text = galleryType.getDisplayName(),
            modifier = Modifier.clickable {
                updatedOnTitleClicked?.invoke()
            }
        )
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            count = images.itemCount,
            state = pagerState
        ) { page ->
            images[page]?.let {
                CatImageCard(
                    catImage = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { updatedOnImageClicked?.invoke() }
                )
            }
        }
    }
}

@Composable
private fun CatImageCard(
    catImage: CatImage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        CatLoader(
            imageUrl = catImage.url,
            imageLoader = ImageLoader.Builder(LocalContext.current)
                .components {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }.build(),
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    }
}