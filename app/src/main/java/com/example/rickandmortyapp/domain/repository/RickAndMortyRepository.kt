package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.remote.dto.CharacterDto
import com.example.rickandmortyapp.data.remote.dto.ResponseLocation

interface RickAndMortyRepository {

    suspend fun getLocation(page : Int) : ResponseLocation

    suspend fun getCharacter(characters : String) : List<CharacterDto>
}