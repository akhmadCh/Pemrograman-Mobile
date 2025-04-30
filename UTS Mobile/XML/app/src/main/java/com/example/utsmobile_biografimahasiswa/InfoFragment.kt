package com.example.utsmobile_biografimahasiswa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.utsmobile_biografimahasiswa.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        // glide for image
        Glide.with(this)
            .load(R.drawable.chaidar)
            .apply(RequestOptions().transform(RoundedCorners(25)))
            .into(binding.imgItemPhotoMahasiswa)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGotoDetail.setOnClickListener {
            val fragDetail = DetailFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(R.id.main, fragDetail, DetailFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
        }
    }
}