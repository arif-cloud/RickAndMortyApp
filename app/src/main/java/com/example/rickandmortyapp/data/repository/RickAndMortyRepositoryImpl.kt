package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import com.example.rickandmortyapp.data.remote.dto.CharacterDto
import com.example.rickandmortyapp.data.remote.dto.ResponseLocation
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val api : RickAndMortyApi
) : RickAndMortyRepository {

    override suspend fun getLocation(page: Int): ResponseLocation {
        return api.getLocation(page)
    }

    override suspend fun getCharacter(characters: String): List<CharacterDto> {
        return api.getCharacter(characters)
    }

}