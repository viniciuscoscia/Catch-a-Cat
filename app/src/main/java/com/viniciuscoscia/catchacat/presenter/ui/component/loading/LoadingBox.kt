package com.viniciuscoscia.catchacat.presenter.ui.component.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun LazyGridScope.loadingState(loadState: CombinedLoadStates, gridColumns: Int) {
    if (loadState.append !is LoadState.Loading && loadState.refresh !is LoadState.Loading) return

    item(span = { GridItemSpan(gridColumns) }) {
        LoadingBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp)
        )
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