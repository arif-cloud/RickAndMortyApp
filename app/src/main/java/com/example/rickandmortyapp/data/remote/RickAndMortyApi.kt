package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.remote.dto.CharacterDto
import com.example.rickandmortyapp.data.remote.dto.ResponseLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("location")
    suspend fun getLocation(@Query("page") page : Int) : ResponseLocation

    @GET("character/{characters}")
    suspend fun getCharacter(@Path("characters") characters : String) : List<CharacterDto>

}