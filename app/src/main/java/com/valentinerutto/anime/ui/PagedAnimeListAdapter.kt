package com.valentinerutto.anime.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.valentinerutto.anime.data.remote.model.Data
import com.valentinerutto.anime.databinding.RowAnimeBinding

class PagedAnimeListAdapter :
    PagingDataAdapter<Data, PagedAnimeListAdapter.AnimeViewHolder>(diff) {

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = getItem(position)
        if (anime != null) {
            holder.bind(anime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return from(parent)
    }

    class AnimeViewHolder(private val binding: RowAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anime: Data) {
            binding.animeTitle.text = anime.title
            binding.imgCover.load(anime.images.jpg.imageUrl)
        }
    }

    companion object {

        fun from(parent: ViewGroup): AnimeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowAnimeBinding.inflate(layoutInflater, parent, false)
            return AnimeViewHolder(binding)
        }

        val diff = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(
                oldItem: Data, newItem: Data
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Data, newItem: Data
            ): Boolean = oldItem.malId == newItem.malId
        }
    }
}