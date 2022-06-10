@file:OptIn(ExperimentalPagerApi::class)

package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
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
import com.viniciuscoscia.catchacat.presenter.ui.component.image.CatImageLoader
import com.viniciuscoscia.catchacat.presenter.ui.component.loading.LoadingBox
import com.viniciuscoscia.catchacat.presenter.ui.model.ImageGallery
import com.viniciuscoscia.catchacat.presenter.ui.theme.CatchACatTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun CatGalleriesScreen(
    navController: NavHostController,
    viewModel: CatGalleriesViewModel = getViewModel()
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
                val pagerState = rememberPagerState()
                val imageGalleries: List<ImageGallery> = viewModel.imageGalleries

                if (imageGalleries.isEmpty()) {
                    LoadingBox(modifier = Modifier.fillMaxSize())
                } else {
                    VerticalPager(
                        count = imageGalleries.size,
                        state = pagerState
                    ) { index ->
                        imageGalleries[index].apply {
                            CatImageCarousel(
                                title = title,
                                catImages = images.collectAsLazyPagingItems()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CatImageCarousel(
    title: String,
    catImages: LazyPagingItems<CatImage>,
    onCatImageClicked: ((imageId: String) -> Unit)? = null
) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = title)
        HorizontalPager(
            count = catImages.itemCount,
            state = pagerState
        ) { page ->
            catImages[page]?.let {
                CatCard(
                    catImage = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onCatImageClicked?.invoke(it.id) }
                )
            }
        }
    }
}

@Composable
private fun CatCard(
    catImage: CatImage,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        CatImageLoader(
            imageUrl = catImage.url,
            imageLoader = ImageLoader.Builder(LocalContext.current)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }.build(),
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
