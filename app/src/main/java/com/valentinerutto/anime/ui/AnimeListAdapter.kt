package com.valentinerutto.anime.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.valentinerutto.anime.data.local.AnimeEntity
import com.valentinerutto.anime.databinding.RowAnimeBinding

class AnimeListAdapter : ListAdapter<AnimeEntity, AnimeListAdapter.AnimeViewHolder>(diff) {

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

        fun bind(anime: AnimeEntity) {
            binding.animeTitle.text = anime.title
            binding.imgCover.load(anime.imgUrl)
        }
    }

    companion object {

        fun from(parent: ViewGroup): AnimeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RowAnimeBinding.inflate(layoutInflater, parent, false)
            return AnimeViewHolder(binding)
        }

        val diff = object : DiffUtil.ItemCallback<AnimeEntity>() {
            override fun areItemsTheSame(
                oldItem: AnimeEntity, newItem: AnimeEntity
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AnimeEntity, newItem: AnimeEntity
            ): Boolean = oldItem.id == newItem.id
        }
    }
}