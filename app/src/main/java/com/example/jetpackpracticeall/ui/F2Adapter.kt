package com.example.jetpackpracticeall.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackpracticeall.data.TodosItem
import com.example.jetpackpracticeall.databinding.Itemf2Binding

class F2Adapter(val data: List<TodosItem>): RecyclerView.Adapter<F2Adapter.F2ViewHolder>() {
    lateinit var bindingf2: Itemf2Binding
    class F2ViewHolder(bindingf2:Itemf2Binding): RecyclerView.ViewHolder(bindingf2.root) {
        val tv = bindingf2.tvF2
        fun bind(item: TodosItem) {
            tv.text = "${item.title}\n ${item.userId}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): F2ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = Itemf2Binding.inflate(layoutInflater, parent, false)
        return F2ViewHolder(binding)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: F2ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}