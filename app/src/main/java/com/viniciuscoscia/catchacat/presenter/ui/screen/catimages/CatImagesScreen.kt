@file:OptIn(ExperimentalFoundationApi::class)

package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun CatImagesScreen(
    navController: NavHostController,
    viewModel: CatImagesViewModel = getViewModel()
) {
    Scaffold(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            val cellState by remember { mutableStateOf(CELL_COUNT) }
            val catImages = viewModel.catImages.collectAsLazyPagingItems()
            LazyVerticalGrid(
                cells = GridCells.Fixed(cellState),
                content = {
                    when (catImages.loadState.refresh) {
                        is LoadState.Error -> {
                            val error = (catImages.loadState.refresh as LoadState.Error).error
                            Timber.e("Load state error") //TODO improve error handling
                            return@LazyVerticalGrid
                        }
                    }
                    items(catImages.itemCount) { index ->
                        Text(text = catImages[index]?.imageUrl ?: "")
                    }
                    renderLoading(catImages.loadState)
                }
            )
        }
    }
}

const val CELL_COUNT = 2

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }
private fun LazyGridScope.renderLoading(loadState: CombinedLoadStates) {
    if (loadState.append is LoadState.Loading) {
        item(span = span) {
            LoadingBox(Modifier.padding(vertical = 20.dp))
        }
    } else if (loadState.refresh is LoadState.Loading) {
        item(span = span) {
            LoadingBox(modifier = Modifier.fillParentMaxSize())
        }
    }
}

@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}