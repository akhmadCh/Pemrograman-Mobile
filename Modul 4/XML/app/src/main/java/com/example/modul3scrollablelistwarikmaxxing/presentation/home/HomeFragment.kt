package com.example.modul3scrollablelistwarikmaxxing.presentation.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3scrollablelistwarikmaxxing.R
import com.example.modul3scrollablelistwarikmaxxing.adapter.ListCharacterAdapter
import com.example.modul3scrollablelistwarikmaxxing.databinding.FragmentHomeBinding
import com.example.modul3scrollablelistwarikmaxxing.models.Character
import com.example.modul3scrollablelistwarikmaxxing.presentation.detail.DetailFragment
import com.example.modul3scrollablelistwarikmaxxing.utils.HomeViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterAdapter: ListCharacterAdapter

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(resources)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeCharacterList()

        viewModel.loadCharacters()
    }

    private fun setupRecyclerView () {
        characterAdapter = ListCharacterAdapter(
            requireContext(),
            ArrayList(),
            onInstagramClick = { link ->
                Log.e("Intent to Instagram", "Going to $link")

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)
            },
            onDetailsClick = { name, warikName, photo, details ->
                val detailFragment = DetailFragment().apply {
                    Log.e("Move to detail page", "move to $name detail page")

                    arguments = Bundle().apply {
                        putString("EXTRA_NAME", name)
                        putString("EXTRA_NAME2", warikName)
                        putInt("EXTRA_PHOTO", photo)
                        putString("EXTRA_DETAILS", details)
                    }
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.main, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.rcCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeCharacterList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characterList.collectLatest { list ->
                characterAdapter.setData(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}