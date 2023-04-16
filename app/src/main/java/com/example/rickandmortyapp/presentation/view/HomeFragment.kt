package com.example.rickandmortyapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.presentation.character_list.CharacterAdapter
import com.example.rickandmortyapp.presentation.location_list.LocationAdapter
import com.example.rickandmortyapp.databinding.FragmentHomeBinding
import com.example.rickandmortyapp.presentation.location_list.LocationLoadStateAdapter
import com.example.rickandmortyapp.presentation.viewmodel.CharacterViewModel
import com.example.rickandmortyapp.presentation.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val locationViewModel: LocationViewModel by viewModels()
    private val characterViewModel: CharacterViewModel by viewModels()
    private val locationAdapter = LocationAdapter()
    private val characterAdapter = CharacterAdapter()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        binding.apply {
            lifecycleScope.launch {
                locationViewModel.locationList.collect{
                    locationAdapter.submitData(it)
                }
            }

            locationRecyclerView.adapter = locationAdapter.withLoadStateFooter(
                footer = LocationLoadStateAdapter()
            )
            locationRecyclerView.layoutManager = LinearLayoutManager(this@HomeFragment.context,LinearLayoutManager.HORIZONTAL,false)


            locationAdapter.onItemClickedListener = {
                lifecycleScope.launch {
                    characterViewModel.getCharacterData(it.residents)
                }
            }

            characterRecyclerView.adapter = characterAdapter
            characterRecyclerView.layoutManager = LinearLayoutManager(this@HomeFragment.context)
        }
    }


    private fun observeLiveData() {
        binding.apply {
            locationViewModel.state.observe(viewLifecycleOwner, Observer {locations ->
                locations?.let {
                    setVisibleLoadingBar(it.isLoading)
                    setErrorMessage(it.error)
                }
            })
            characterViewModel.state.observe(viewLifecycleOwner, Observer {characters ->
                characters?.let {
                    setVisibleLoadingBar(it.isLoading)
                    setErrorMessage(it.error)
                    checkEmptyList(it.isEmpty)
                    characterAdapter.updateCharacterList(it.characters)
                }
            })
        }
    }

    private fun setVisibleLoadingBar(isLoading : Boolean) {
        binding.apply {
            if (isLoading)
                loadingBar.visibility = View.VISIBLE
            else
                loadingBar.visibility = View.GONE
        }
    }

    private fun setErrorMessage(errorMessage : String) {
        binding.apply {
            if (errorMessage.isNotEmpty()) {
                statusTextView.visibility = View.VISIBLE
                statusTextView.text = errorMessage
            } else
                statusTextView.visibility = View.GONE
        }
    }

    private fun checkEmptyList(isEmpty : Boolean) {
        binding.apply {
            if (isEmpty) {
                statusTextView.visibility = View.VISIBLE
                statusTextView.text = "This Location is Empty"
            } else
                statusTextView.visibility = View.GONE
        }
    }
}
