package com.example.rickandmortyapp.presentation.location_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.LocationItemRowBinding
import com.example.rickandmortyapp.domain.model.Location

class LocationAdapter : PagingDataAdapter<Location,LocationAdapter.LocationViewHolder>(differCallback) {
    var selectedPosition = 0
    var onItemClickedListener : (item: Location) -> Unit = {}
    inner class LocationViewHolder(val binding : LocationItemRowBinding) :  RecyclerView.ViewHolder(binding.root){
        fun bind(item : Location) {
            binding.locationNameText.setText(item.name)
            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = layoutPosition
                notifyItemChanged(selectedPosition)
                onItemClickedListener(item)
            }
        }

        fun defaultBg() {
            binding.locationNameText.background = itemView.context.getDrawable(R.drawable.button_background)
        }


        fun selectedBg() {
            binding.locationNameText.background = itemView.context.getDrawable(R.drawable.selected_background)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LocationItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        if(position == selectedPosition)
            holder.selectedBg()
        else
            holder.defaultBg()
        holder.bind(getItem(position)!!)
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem == newItem
            }
        }
    }
}