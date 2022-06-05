package com.viniciuscoscia.catchacat.presenter.util

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.isLoading(): Boolean {
    return append is LoadState.Loading || refresh is LoadState.Loading
}