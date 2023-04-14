package com.example.rickandmortyapp.data.remote.dto

import com.example.rickandmortyapp.domain.model.Location

data class ResponseLocation(
    val info : Info,
    val results : ArrayList<LocationDto>
)
