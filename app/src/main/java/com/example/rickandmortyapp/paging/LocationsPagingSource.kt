package com.example.rickandmortyapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.data.remote.dto.toLocation
import com.example.rickandmortyapp.domain.model.Location
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.delay
import retrofit2.HttpException

class LocationsPagingSource(private val repository: RickAndMortyRepository) : PagingSource<Int, Location>() {

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        return try {
            val currentPage = params.key ?: 1
            val data = repository.getLocation(currentPage).results.map { it.toLocation() }
            val responseData = mutableListOf<Location>()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        } catch (httpE : HttpException) {
            LoadResult.Error(httpE)
        }
    }
}