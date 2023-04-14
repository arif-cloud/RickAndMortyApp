package com.example.rickandmortyapp.presentation.character_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.CharacterItemRowFemaleBinding
import com.example.rickandmortyapp.databinding.CharacterItemRowMaleBinding
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.presentation.view.HomeFragmentDirections
import java.lang.IllegalArgumentException

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    var characterList : ArrayList<Character> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return when(viewType) {
            R.layout.character_item_row_female -> CharacterViewHolder.FemaleViewHolder(
                CharacterItemRowFemaleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            R.layout.character_item_row_male -> CharacterViewHolder.MaleViewHolder(
                CharacterItemRowMaleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provider")
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        when(holder) {
            is CharacterViewHolder.FemaleViewHolder -> holder.bind(characterList[position])
            is CharacterViewHolder.MaleViewHolder -> holder.bind(characterList[position])
        }
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(characterList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCharacterList(newCharacterList : ArrayList<Character>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (characterList[position].gender == "Female" || characterList[position].gender == "unknown")
            return R.layout.character_item_row_female
        if (characterList[position].gender == "Male" || characterList[position].gender == "Genderless")
            return R.layout.character_item_row_male
        else
            return R.layout.character_item_row_male
    }
}