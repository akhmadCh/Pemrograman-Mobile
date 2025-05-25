package com.example.beeceptorapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beeceptorapi.databinding.ItemTodosBinding
import com.example.beeceptorapi.models.Todos

class TodosAdapter (
    private val dataTodos: List<Todos>
) : RecyclerView.Adapter<TodosAdapter.DataViewHolder>() {

    inner class DataViewHolder (val binding: ItemTodosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = dataTodos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = dataTodos[position]
        holder.binding.tvTodosTitle.text = item.title
    }

}