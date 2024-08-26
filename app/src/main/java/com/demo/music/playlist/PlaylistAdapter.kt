package com.demo.music.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.databinding.ItemPlaylistBinding

class PlaylistAdapter : ListAdapter<Playlist, PlaylistViewHolder>(PlaylistDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PlaylistViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class PlaylistDiffUtil : DiffUtil.ItemCallback<Playlist>() {
    override fun areItemsTheSame(
        oldItem: Playlist,
        newItem: Playlist,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Playlist,
        newItem: Playlist,
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
