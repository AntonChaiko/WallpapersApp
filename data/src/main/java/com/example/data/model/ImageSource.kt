package com.example.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.constants.DEFAULT_PAGE_SIZE
import com.example.data.remote.api.ImageApiService
import retrofit2.HttpException
import java.io.IOException


class ImageSource(
    private val query: String,
    private val imageApiService: ImageApiService
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val position: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        return try {

            val response = imageApiService.getImages(query, position, pageSize)
            val repos = response.items
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + (params.loadSize / DEFAULT_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }
}