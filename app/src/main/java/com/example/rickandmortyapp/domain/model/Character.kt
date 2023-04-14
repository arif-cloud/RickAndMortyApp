package com.example.rickandmortyapp.domain.model

import android.os.Parcelable
import com.example.rickandmortyapp.data.remote.dto.CharacterLocation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val location: CharacterLocation,
    val origin: CharacterLocation
) : Parcelable