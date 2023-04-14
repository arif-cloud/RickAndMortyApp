package com.example.rickandmortyapp.presentation.location_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.LocationItemRowBinding
import com.example.rickandmortyapp.databinding.ProgressRowBinding

class LocationLoadStateAdapter : LoadStateAdapter<LocationLoadStateAdapter.LoadStateViewHolder>(){
    inner class LoadStateViewHolder(val binding : ProgressRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState) {
            binding.apply {
                loadStateProgress.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = ProgressRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(view)
    }
}