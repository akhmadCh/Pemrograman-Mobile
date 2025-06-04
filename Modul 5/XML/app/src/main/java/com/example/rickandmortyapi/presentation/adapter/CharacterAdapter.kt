package com.example.rickandmortyapi.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.data.model.CharacterDto
import com.example.rickandmortyapi.databinding.ItemRowMortyBinding

class CharacterAdapter (
    private val dataMorty: ArrayList<CharacterDto>,
    private val onDetailsClick: (String, String, String, String) -> Unit,
    private val onWikisClick: (String) -> Unit,
    private val onFavoriteClick: (CharacterDto) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    inner class MyViewHolder (val binding: ItemRowMortyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowMortyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataMorty.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataMorty[position]

        holder.binding.apply {
            itemNameMorty.text = item.name
            itemSpeciesMorty.text = item.species
            itemAliveMorty.text = item.status

            Glide.with(itemImageMorty)
                .load(item.image)
                .error(R.drawable.ic_launcher_background)
                .into(itemImageMorty)

            btnDetails.setOnClickListener {
                onDetailsClick(item.image, item.name, item.species, item.status)
            }

            btnWiki.setOnClickListener {
                onWikisClick(item.name ?: "")
            }

            btnFavorite.setOnClickListener {
                onFavoriteClick(item)
            }

            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context, item.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData (newData: List<CharacterDto>) {
        if (newData.isNotEmpty()) {
            dataMorty.clear()
            dataMorty.addAll(newData)
            notifyDataSetChanged()
        }

    }
}