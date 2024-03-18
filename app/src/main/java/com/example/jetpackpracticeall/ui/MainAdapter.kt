package com.example.jetpackpracticeall.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackpracticeall.R
import com.example.jetpackpracticeall.data.UserItem

class MainAdapter(
    private val users: ArrayList<UserItem>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_avatar = itemView.findViewById<ImageView>(R.id.imageViewAvatar)
        val tv_email = itemView.findViewById<TextView>(R.id.textViewUserEmail)
        val tv_name = itemView.findViewById<TextView>(R.id.textViewUserName)
        fun bind(user: UserItem) {
            tv_name.text = user.name
            tv_email.text = user.email
            Glide.with(img_avatar.context)
                .load(user.avatar)
                .into(img_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<UserItem>) {
        users.addAll(list)
    }

}