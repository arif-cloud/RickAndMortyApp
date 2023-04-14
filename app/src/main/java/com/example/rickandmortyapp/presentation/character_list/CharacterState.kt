package com.example.rickandmortyapp.presentation.character_list

import com.example.rickandmortyapp.domain.model.Character

data class CharacterState(
    val isLoading : Boolean = false,
    val characters : ArrayList<Character> = arrayListOf(),
    val error : String = "",
    val isEmpty : Boolean = false
)
