package com.example.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val newsDataList: List<NewsData>,
    private val onLikeToggled: (position: Int, liked: Boolean) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsData = newsDataList[position]
        holder.title.text = newsData.title
        holder.description.text = newsData.description
        holder.url = newsData.url

        Picasso.get()
            .load(newsData.urlToImage)
            .into(holder.imageView)

        holder.btnLike.setImageResource(
            if (newsData.liked) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        )

        holder.btnLike.setOnClickListener {
            val newState = !newsData.liked
            newsData.liked = newState
            notifyItemChanged(position)
            onLikeToggled(position, newState)
        }
    }

    override fun getItemCount(): Int = newsDataList.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parent: LinearLayout = itemView.findViewById(R.id.parent)
        val title: TextView = itemView.findViewById(R.id.newsTitle)
        val description: TextView = itemView.findViewById(R.id.description)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        var url: String? = null

        val btnLike: ImageButton = itemView.findViewById(R.id.btnLike)

        init {
            itemView.setOnClickListener {

            }
        }
    }
}
