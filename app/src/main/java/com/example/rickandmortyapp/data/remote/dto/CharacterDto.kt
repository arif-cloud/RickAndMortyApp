package com.example.rickandmortyapp.data.remote.dto

import com.example.rickandmortyapp.domain.model.Character

data class CharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val location: CharacterLocation,
    val origin: CharacterLocation
)

fun CharacterDto.toCharacter() : Character {
    return Character(
        id = id,
        name = name,
        image = image,
        status = status,
        species = species,
        gender = gender,
        origin = origin,
        location = location,
        episode = episode,
        created = created
    )
}
