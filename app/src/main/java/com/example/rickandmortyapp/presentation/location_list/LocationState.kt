package com.example.rickandmortyapp.presentation.location_list

import androidx.paging.PagingData
import com.example.rickandmortyapp.domain.model.Location
import com.example.rickandmortyapp.paging.LocationsPagingSource
import kotlinx.coroutines.flow.Flow

data class LocationState(
    val isLoading : Boolean = false,
    val locations : LocationsPagingSource? = null,
    val error : String = ""
)
