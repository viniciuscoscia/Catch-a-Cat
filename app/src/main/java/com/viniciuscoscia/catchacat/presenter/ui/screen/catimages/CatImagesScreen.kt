@file:OptIn(ExperimentalFoundationApi::class)

package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.presenter.ui.component.image.ImageLoader
import com.viniciuscoscia.catchacat.presenter.ui.component.loading.LoadingBox
import com.viniciuscoscia.catchacat.presenter.ui.component.loading.loadingState
import com.viniciuscoscia.catchacat.presenter.util.isLoading
import org.koin.androidx.compose.getViewModel

private const val GRID_COLUMNS = 2

@Composable
fun CatImagesScreen(
    navController: NavHostController,
    viewModel: CatImagesViewModel = getViewModel()
) {
    Scaffold(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            val catImages = viewModel.catImages.collectAsLazyPagingItems()
            CatImageGrid(catImages) {

            }
        }
    }
}

@Composable
fun CatImageGrid(catImages: LazyPagingItems<CatImage>, onCatImageClicked: () -> Unit) {
    val cellState by remember { mutableStateOf(GRID_COLUMNS) }
    LazyVerticalGrid(
        cells = GridCells.Fixed(cellState),
        content = {
            if (catImages.loadState.refresh is LoadState.Error) {
                item(span = { GridItemSpan(GRID_COLUMNS) }) {
                    LoadingBox(
                        modifier = Modifier.fillParentMaxSize()
                    )
                }
            }
            items(catImages.itemCount) { index ->
                catImages[index]?.let {
                    Card {
                        Column {
                            ImageLoader(imageUrl = it.imageUrl, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
            if (catImages.loadState.isLoading()) {
                loadingState(catImages.loadState, GRID_COLUMNS)
            }
        }
    )
}
