package com.example.rickandmortyapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var binding : FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var episodes = ""
        val episodeList = arrayListOf<String>()
        binding.apply {
            detailTitleTextView.setText(args.currentCharacter.name)
            Glide.with(this@DetailFragment).load(args.currentCharacter.image).into(detailImageView)
            detailStatusTextView.setText(args.currentCharacter.status)
            detailSpecyTextView.setText(args.currentCharacter.species)
            detailOriginTextView.setText(args.currentCharacter.origin.name)
            detailLocationTextView.setText(args.currentCharacter.location.name)
            detailGenderTextView.setText(args.currentCharacter.gender)
            detailCreatedTextView.setText(args.currentCharacter.created)
            args.currentCharacter.episode.map {
                val episode = it.substring(it.lastIndexOf("/")+1)
                episodeList.add(episode)
            }
            episodes = episodeList.joinToString(",")
            detailEpisodeTextView.setText(episodes)

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}