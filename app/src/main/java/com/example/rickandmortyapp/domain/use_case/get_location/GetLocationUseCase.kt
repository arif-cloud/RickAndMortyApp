package com.example.rickandmortyapp.domain.use_case.get_location

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.data.remote.dto.LocationDto
import com.example.rickandmortyapp.data.remote.dto.toLocation
import com.example.rickandmortyapp.domain.model.Location
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import com.example.rickandmortyapp.paging.LocationsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository : RickAndMortyRepository
) {

    operator fun invoke() : Flow<Resource<LocationsPagingSource>> = flow {
        try {
            emit(Resource.Loading())
            val locationList = LocationsPagingSource(repository)
            emit(Resource.Success(locationList))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}