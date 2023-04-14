package com.example.rickandmortyapp.domain.model

data class Location(
    val id: Int? = null,
    val name: String? = null,
    val residents: List<String>? = null
)