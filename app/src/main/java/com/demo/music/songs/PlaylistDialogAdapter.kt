package com.demo.music.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.data.AddPlaylist
import com.demo.data.Playlist
import com.demo.databinding.ItemNewPlaylistBinding
import com.demo.databinding.ItemPlaylistDialogBinding

class PlaylistDialogAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(PlaylistDialogDiffUntill()) {
    companion object {
        const val TYPE_ADD_PLAYLIST = 1
        const val TYPE_PLAYLIST = 2
    }

    override fun getItemViewType(position: Int): Int {
        when (getItem(position)) {
            is AddPlaylist -> return TYPE_ADD_PLAYLIST
            is Playlist -> return TYPE_PLAYLIST
            else -> return super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ADD_PLAYLIST -> {
                val binding = ItemNewPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemNewPlaylistViewHolder(binding)
            }
            else -> {
                val binding = ItemPlaylistDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemPlaylistViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        if (holder is ItemNewPlaylistViewHolder) {
            holder.bindData(item)
        }
        if (holder is ItemPlaylistViewHolder) {
            holder.bindData(item)
        }
    }
}

class PlaylistDialogDiffUntill : ItemCallback<Any>() {
    override fun areItemsTheSame(
        oldItem: Any,
        newItem: Any,
    ): Boolean {
        if (oldItem is Playlist && newItem is Playlist) {
            return oldItem.id == newItem.id
        } else if (oldItem is AddPlaylist && newItem is AddPlaylist) {
            return oldItem.id == newItem.id
        }
        return false
    }

    override fun areContentsTheSame(
        oldItem: Any,
        newItem: Any,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
