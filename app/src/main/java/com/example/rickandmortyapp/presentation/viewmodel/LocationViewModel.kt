package com.example.rickandmortyapp.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.domain.use_case.get_location.GetLocationUseCase
import com.example.rickandmortyapp.paging.LocationsPagingSource
import com.example.rickandmortyapp.presentation.location_list.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUseCase: GetLocationUseCase
) : ViewModel(){

    val locationList = Pager(PagingConfig(1)) {
        _state.value?.locations!!
    }.flow.cachedIn(viewModelScope)

    private val _state = MutableLiveData<LocationState>()
    val state : LiveData<LocationState> get() = _state

    init {
        getLocation()
    }

    private fun getLocation() {
        locationUseCase().onEach {result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = LocationState(locations = result.data)
                }
                is Resource.Loading -> {
                    _state.value = LocationState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = LocationState(error = result.message ?: "Error!")
                }
            }
        }.launchIn(viewModelScope)
    }
}
