package com.demo.screen.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.data.model.Playlist
import com.demo.databinding.ItemPlaylistBinding

/*
class PlaylistAdapter(
    val onPlaylistClick: (Albums) -> Unit,
) : ListAdapter<Albums, PlaylistViewHolder>(PlaylistDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlaylistViewHolder {
        val binding =
            ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding, onPlaylistClick = {
            val item = getItem(it)
            onPlaylistClick.invoke(item)
        })
    }

    override fun onBindViewHolder(
        holder: PlaylistViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class PlaylistDiffUtil : DiffUtil.ItemCallback<Albums>() {
    override fun areItemsTheSame(
        oldItem: Albums,
        newItem: Albums,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Albums,
        newItem: Albums,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
*/

class PlaylistAdapter(
    val onPlaylistClick: (Playlist) -> Unit,
) : ListAdapter<Playlist, PlaylistViewHolder>(PlaylistDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding, onPlaylistClick = {
            val item = getItem(it)
            onPlaylistClick.invoke(item)
        })
    }

    override fun onBindViewHolder(
        holder: PlaylistViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
    }
}

class PlaylistDiffCallback : DiffUtil.ItemCallback<Playlist>() {
    override fun areItemsTheSame(
        oldItem: Playlist,
        newItem: Playlist,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Playlist,
        newItem: Playlist,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
