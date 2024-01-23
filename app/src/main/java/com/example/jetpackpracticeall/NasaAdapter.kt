package com.example.jetpackpracticeall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackpracticeall.models.Photo

class NasaAdapter(private val dataList: List<Photo>): RecyclerView.Adapter<NasaAdapter.NasaViewHolder>() {
    class NasaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // wire all views
        private val tvId = view.findViewById<TextView>(R.id.photo_id)
        private val tvSource = view.findViewById<TextView>(R.id.source)
        private val tvSol = view.findViewById<TextView>(R.id.sol)
        private val tvEarthDate = view.findViewById<TextView>(R.id.earth_date)
        //bind the views created to the photo list item
        fun bind(photo: Photo) {
            tvId.text = photo.id.toString()
            tvEarthDate.text = photo.earth_date
            tvSol.text = photo.sol.toString()
            tvSource.text = photo.img_src
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return NasaViewHolder(view)
    }

    override fun getItemCount():Int = dataList.size

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}