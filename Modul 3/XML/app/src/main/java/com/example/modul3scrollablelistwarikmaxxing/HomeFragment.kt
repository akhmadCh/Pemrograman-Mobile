package com.example.modul3scrollablelistwarikmaxxing

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3scrollablelistwarikmaxxing.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterAdapter: ListCharacterAdapter
    private val list = ArrayList<Character>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        list.clear()
        list.addAll(getListCharacter())
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView () {
        characterAdapter = ListCharacterAdapter(
            requireContext(),
            list,
            onInstagramClick = { link ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)
            },
            onDetailsClick = { name, warikName, photo, details ->
                val detailFragment = DetailFragment().apply {
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

    @SuppressLint("Recycle")
    private fun getListCharacter (): ArrayList<Character> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataWarikName = resources.getStringArray(R.array.data_warik_name)
        val dataLinkInstagram = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDetails = resources.getStringArray(R.array.data_details)
        val listCharacter = ArrayList<Character>()

        for (i in dataName.indices) {
            val character =
                Character(dataName[i], dataWarikName[i], dataLinkInstagram[i], dataPhoto.getResourceId(i, -1), dataDetails[i])
            listCharacter.add(character)
        }
        dataPhoto.recycle()
        return listCharacter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}