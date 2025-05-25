package com.example.beeceptorapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beeceptorapi.API.ApiConfig
import com.example.beeceptorapi.adapter.TodosAdapter
import com.example.beeceptorapi.databinding.FragmentMainBinding
import com.example.beeceptorapi.models.Todos
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var todosAdapter: TodosAdapter
    private val list = ArrayList<Todos>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        list.clear()
        setupRecyclerView()
        getTodos()

        return binding.root
    }

    private fun setupRecyclerView () {
        todosAdapter = TodosAdapter(list)

        binding.rvTodos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todosAdapter
            setHasFixedSize(true)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getTodos () {
        lifecycleScope.launch {
            try {
                val response = ApiConfig.instance.getTodos()
                if (response.isSuccessful) {
                    response.body()?.let {
                        list.clear()
                        list.addAll(it)
                        todosAdapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}