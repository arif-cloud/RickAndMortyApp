package com.example.rickandmortyapp.domain.use_case.get_character

import com.example.rickandmortyapp.common.Resource
import com.example.rickandmortyapp.data.remote.dto.toCharacter
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository : RickAndMortyRepository
) {
    operator fun invoke(characters : String) : Flow<Resource<ArrayList<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val characterList = repository.getCharacter(characters).map { it.toCharacter() }
            val characterArrayList = ArrayList(characterList)
            emit(Resource.Success(characterArrayList))
        } catch (e : HttpException) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}