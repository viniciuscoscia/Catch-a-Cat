package com.viniciuscoscia.catchacat.presenter.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viniciuscoscia.catchacat.domain.entity.CatRandomImage
import com.viniciuscoscia.catchacat.domain.usecase.GetCatImagesUseCase

class CatImagesPager(private val getCatImagesUseCase: GetCatImagesUseCase) {
    fun getCatImagesFlow() = Pager(
        config = PagingConfig(
            pageSize = 12,
            prefetchDistance = 6
        ),
        pagingSourceFactory = { CatImagesPagingSource(getCatImagesUseCase) }
    ).flow
}

private class CatImagesPagingSource(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : PagingSource<Int, CatRandomImage>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatRandomImage> {
        val pageIndex = if (params is LoadParams.Refresh) {
            STARTING_PAGE_INDEX
        } else {
            params.key ?: STARTING_PAGE_INDEX
        }

        val result = getCatImagesUseCase(pageIndex)

        return if (result.isSuccess && result.getOrNull() != null) {
            LoadResult.Page(
                data = result.getOrThrow(),
                prevKey = params.key,
                nextKey = pageIndex.plus(1)
            )
        } else {
            LoadResult.Error(result.exceptionOrNull() ?: error("Get cat images paging error"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatRandomImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}