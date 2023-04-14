package com.example.rickandmortyapp.presentation.character_list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.CharacterItemRowFemaleBinding
import com.example.rickandmortyapp.databinding.CharacterItemRowMaleBinding
import com.example.rickandmortyapp.domain.model.Character

sealed class CharacterViewHolder(binding : ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class MaleViewHolder(private val binding : CharacterItemRowMaleBinding) : CharacterViewHolder(binding) {
        fun bind (item : Character) {
            binding.apply {
                characterNameTextView.setText(item.name)
                Glide.with(itemView.context).load(item.image).into(characterImageView)
                if (item.gender == "Male")
                    characterGenderImageView.setImageResource(R.drawable.male)
                if (item.gender == "Genderless")
                    characterGenderImageView.setImageResource(R.drawable.genderless)
            }
        }
    }

    class FemaleViewHolder(private val binding : CharacterItemRowFemaleBinding) : CharacterViewHolder(binding) {
        fun bind (item : Character) {
            binding.apply {
                characterNameTextView.setText(item.name)
                Glide.with(itemView.context).load(item.image).into(characterImageView)
                if (item.gender == "Female")
                    characterGenderImageView.setImageResource(R.drawable.female)
                if (item.gender == "unknown")
                    characterGenderImageView.setImageResource(R.drawable.unknown)
            }
        }
    }
}
