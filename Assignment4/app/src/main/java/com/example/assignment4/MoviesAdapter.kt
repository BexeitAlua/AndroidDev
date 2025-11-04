package com.example.assignment4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val items: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<MoviesAdapter.VH>() {

    fun submit(newItems: List<Movie>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.poster)
        val title: TextView = itemView.findViewById(R.id.title)
        val overview: TextView = itemView.findViewById(R.id.overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.overview.text = item.overview

        val posterUrl = item.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }

        Glide.with(holder.poster.context)
            .load(posterUrl)
            .into(holder.poster)
    }

    override fun getItemCount(): Int = items.size
}
