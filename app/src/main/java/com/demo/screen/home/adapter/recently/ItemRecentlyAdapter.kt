package com.demo.screen.home.adapter.recently

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Artist
import com.demo.data.model.Songs
import com.demo.databinding.ItemArtistRecentlyBinding
import com.demo.databinding.ItemSongRecentlyBinding

class ItemRecentlyAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(ItemRecentlyDiffUtil()) {
    companion object {
        const val TYPE_ARTIST = 1
        const val TYPE_SONG = 2
    }

    override fun getItemViewType(position: Int): Int {
        when (getItem(position)) {
            is Artist -> return TYPE_ARTIST
            is Songs -> return TYPE_SONG
            else -> return super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ARTIST -> {
                val binding = ItemArtistRecentlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemArtistRecentlyViewHolder(binding)
            }
            TYPE_SONG -> {
                val binding = ItemSongRecentlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemSongRecentlyViewHolder(binding)
            }
            else -> {
                val binding = ItemArtistRecentlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemArtistRecentlyViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        if (holder is ItemArtistRecentlyViewHolder) {
            holder.bindData(item)
        } else {
            if (holder is ItemSongRecentlyViewHolder) {
                holder.bindData(item)
            }
        }
    }
}

class ItemRecentlyDiffUtil : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(
        oldItem: Any,
        newItem: Any,
    ): Boolean {
        if (oldItem is Artist && newItem is Artist) {
            return oldItem.id == newItem.id
        } else if (oldItem is Songs && newItem is Songs) {
            return oldItem.id == newItem.id
        } else {
            return false
        }
    }

    override fun areContentsTheSame(
        oldItem: Any,
        newItem: Any,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
