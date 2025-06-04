package com.example.rickandmortyapi.presentation.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapi.data.repository.CharacterRepository
import com.example.rickandmortyapi.databinding.FragmentFavoriteBinding
import com.example.rickandmortyapi.presentation.adapter.FavoriteAdapter
import com.example.rickandmortyapi.utils.FavoriteViewModelFactory
import com.example.rickandmortyapi.utils.Injection

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository: CharacterRepository = Injection.provideRepository(requireContext())
        val factory = FavoriteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        favoriteAdapter = FavoriteAdapter(arrayListOf()) {
            viewModel.removeFavorite(it)
            Toast.makeText(requireContext(), "${it.name} dihapus dari favorit", Toast.LENGTH_SHORT).show()
        }

        // Pasang adapter ke RecyclerView
        binding.rvFavorite.adapter = favoriteAdapter

        // Observe data LiveData dari Room
        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            favoriteAdapter.setData(favorites)
        }
    }
}