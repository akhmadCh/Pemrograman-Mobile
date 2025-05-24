package com.example.modul3scrollablelistwarikmaxxing.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.modul3scrollablelistwarikmaxxing.models.Character
import com.example.modul3scrollablelistwarikmaxxing.databinding.ItemCharacterBinding

class ListCharacterAdapter(
    private val context: Context,
    private val listCharacter: ArrayList<Character>,
    private val onDetailsClick:(String, String, Int, String) -> Unit,
    private val onInstagramClick:(String) -> Unit
): RecyclerView.Adapter<ListCharacterAdapter.DataViewHolder>() {

    inner class DataViewHolder (val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        with(holder) {
            with(listCharacter[position]) {
                binding.tvItemName.text = this.name
                binding.tvItemWarikName.text = this.warikName
                Glide.with(context)
                    .load(this.photo)
                    .apply(RequestOptions().transform(RoundedCorners(15)))
                    .into(binding.imgItemPhoto)
//                binding.imgItemPhoto.setImageResource(this.photo)
                binding.btnGotoInstagram.setOnClickListener{
                    onInstagramClick(this.linkInstagram)
                }
                binding.btnDetails.setOnClickListener {
                    onDetailsClick(this.name, this.warikName, this.photo, this.details)
                }
            }
        }
    }

    override fun getItemCount(): Int = listCharacter.size

    fun setData (newList: List<Character>) {
        listCharacter.clear()
        listCharacter.addAll(newList)
    }
}