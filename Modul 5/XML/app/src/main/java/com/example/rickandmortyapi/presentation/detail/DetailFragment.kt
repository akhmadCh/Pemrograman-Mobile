package com.example.rickandmortyapi.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val name = arguments?.getString("EXTRA_NAME")
        val species = arguments?.getString("EXTRA_SPECIES")
        val status = arguments?.getString("EXTRA_STATUS")
        val photo = arguments?.getString("EXTRA_PHOTO")

        binding.tvName.text = name
        binding.tvSpecies.text = species
        binding.tvStatus.text = status

        Glide.with(this)
            .load(photo)
            .circleCrop()
            .into(binding.imgItemPhoto)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}