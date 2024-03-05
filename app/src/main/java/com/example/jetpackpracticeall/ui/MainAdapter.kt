package com.example.jetpackpracticeall.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackpracticeall.data.TodosItem
import com.example.jetpackpracticeall.databinding.ItemsBinding

class MainAdapter(val data: List<TodosItem>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    lateinit var binding: ItemsBinding
    class ViewHolder(binding: ItemsBinding): RecyclerView.ViewHolder(binding.root) {
        val tv = binding.itemValue
        fun bind(todosItem: TodosItem) {
            tv.text = "${todosItem.id} \n ${todosItem.title} \n ${todosItem.userId}"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount():Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}