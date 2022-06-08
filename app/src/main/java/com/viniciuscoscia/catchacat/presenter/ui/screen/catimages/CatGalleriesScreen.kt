@file:OptIn(ExperimentalPagerApi::class)

package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

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
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.viniciuscoscia.catchacat.domain.entity.CatRandomImage
import com.viniciuscoscia.catchacat.presenter.ui.component.image.ImageLoader
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
                val imageGalleries = viewModel.imageGalleries

                VerticalPager(
                    count = imageGalleries.size,
                    state = pagerState
                ) { index ->
                    imageGalleries[index].apply {
                        CatImageCarousel(
                            title = title,
                            catImages = images.collectAsLazyPagingItems()
                        ) {

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
    catImages: LazyPagingItems<CatRandomImage>,
    onCatImageClicked: (imageId: String) -> Unit
) {
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = title)
        HorizontalPager(
            count = catImages.itemCount,
            state = pagerState
        ) { page ->
            // Our page content
            catImages[page]?.let {
                CatCard(
                    catImage = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onCatImageClicked(it.id) }
                )
            }
        }
    }
}

@Composable
private fun CatCard(
    catImage: CatRandomImage,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        ImageLoader(
            imageUrl = catImage.imageUrl,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
