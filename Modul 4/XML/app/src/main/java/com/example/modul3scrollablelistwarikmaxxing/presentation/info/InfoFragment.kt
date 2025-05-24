package com.example.modul3scrollablelistwarikmaxxing.presentation.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.modul3scrollablelistwarikmaxxing.R
import com.example.modul3scrollablelistwarikmaxxing.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        // glide image
        Glide.with(this)
            .load(R.drawable.img)
            .apply(RequestOptions().transform(RoundedCorners(25)))
            .into(binding.imgItemPhotoWarik)

        return binding.root
    }
}