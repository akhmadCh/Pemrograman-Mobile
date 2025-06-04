package com.example.rickandmortyapi.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapi.data.model.CharacterEntity
import com.example.rickandmortyapi.databinding.ItemRowFavoriteBinding

class FavoriteAdapter(
    private val items: ArrayList<CharacterEntity>,
    private val onRemoveClick: (CharacterEntity) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    inner class FavViewHolder(val binding: ItemRowFavoriteBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemRowFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            itemNameFav.text = item.name
            itemSpeciesFav.text = item.species
            itemAliveFav.text = item.status

            Glide.with(itemImageFav.context)
                .load(item.image)
                .into(itemImageFav)

            btnRemoveFav.setOnClickListener {
                onRemoveClick(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<CharacterEntity>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }
}