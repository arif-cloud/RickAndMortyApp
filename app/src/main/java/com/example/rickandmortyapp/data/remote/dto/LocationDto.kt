package com.example.rickandmortyapp.data.remote.dto

import com.example.rickandmortyapp.domain.model.Location

data class LocationDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)

fun LocationDto.toLocation() : Location {
    return Location(
        id = id,
        name = name,
        residents = residents
    )
}
