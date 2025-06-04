package com.example.rickandmortyapi.presentation.home

import HomeViewModel
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.Resource
import com.example.rickandmortyapi.data.repository.CharacterRepositoryInstance
import com.example.rickandmortyapi.databinding.FragmentHomeBinding
import com.example.rickandmortyapi.presentation.adapter.CharacterAdapter
import com.example.rickandmortyapi.presentation.detail.DetailFragment
import com.example.rickandmortyapi.utils.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mortyAdapter: CharacterAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = CharacterRepositoryInstance.getRepository(requireContext())
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView () {
        mortyAdapter = CharacterAdapter(
            arrayListOf(),
            onDetailsClick = { photo, name, species, status ->
                val detailFragment = DetailFragment().apply {
                    Log.e("Move to detail page", "move to $name detail page")

                    arguments = Bundle().apply {
                        putString("EXTRA_NAME", name)
                        putString("EXTRA_SPECIES", species)
                        putString("EXTRA_STATUS", status)
                        putString("EXTRA_PHOTO", photo)
                    }
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.navigation_content, detailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            onWikisClick = { name ->
                val query = name.replace(" ", "_")
                val wikiUrl = "https://rickandmorty.fandom.com/wiki/$query"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl))
                startActivity(intent)
            },
            onFavoriteClick = { character ->
                viewModel.addFavorite(character)
                Toast.makeText(requireContext(), "${character.name} ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
            }
        )

        binding.rvMorty.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mortyAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterState.collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvMorty.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvMorty.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                        mortyAdapter.updateData(result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvMorty.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), result.message ?: "Unknown Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}