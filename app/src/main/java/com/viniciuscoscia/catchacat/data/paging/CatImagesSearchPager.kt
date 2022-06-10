package com.viniciuscoscia.catchacat.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viniciuscoscia.catchacat.domain.entity.CatImage
import com.viniciuscoscia.catchacat.domain.entity.imagesearch.ImageSearchParam
import com.viniciuscoscia.catchacat.domain.repository.CatRepository

class CatImagesSearchPager(private val catRepository: CatRepository) {
    fun searchForPagingImages(searchParams: List<ImageSearchParam>? = null) = Pager(
        config = PagingConfig(
            pageSize = 12,
            prefetchDistance = 6
        ),
        pagingSourceFactory = { CatImagesPagingSource(catRepository, searchParams) }
    ).flow
}

private class CatImagesPagingSource(
    private val catRepository: CatRepository,
    private val searchParams: List<ImageSearchParam>? = null
) : PagingSource<Int, CatImage>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImage> {
        val pageIndex = if (params is LoadParams.Refresh) {
            STARTING_PAGE_INDEX
        } else {
            params.key ?: STARTING_PAGE_INDEX
        }

        val result = catRepository.getCatImages(pageIndex, searchParams)

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

    override fun getRefreshKey(state: PagingState<Int, CatImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}