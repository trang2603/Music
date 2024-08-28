package com.demo.home.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.Add
import com.demo.data.Songs
import com.demo.databinding.ItemAddFavouriteBinding
import com.demo.databinding.ItemSongFavouriteBinding

class ItemFavouriteAdapter: ListAdapter<Any, RecyclerView.ViewHolder>(ItemFavouriteDiffCallback()) {
    companion object {
        const val TYPE_SONG = 1
        const val TYPE_ADD = 2
    }

    override fun getItemViewType(position: Int): Int {
        when(getItem(position)) {
            is Songs -> return TYPE_SONG
            else -> return TYPE_ADD
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_SONG -> {
                val binding = ItemSongFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemSongViewHolder(binding)
            }
            else -> {
                val binding = ItemAddFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemAddViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ItemFavouriteDiffCallback: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is Songs && newItem is Songs) {
            return oldItem.id == newItem.id
        }
        else if(oldItem is Add && newItem is Add) {
            return oldItem.id == newItem.id
        }
        else return false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}