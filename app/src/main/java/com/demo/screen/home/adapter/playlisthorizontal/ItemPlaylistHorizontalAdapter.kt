package com.demo.screen.home.adapter.playlisthorizontal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.data.model.Playlist
import com.demo.databinding.ItemPlaylistHomeBinding

class PlaylistHorizontalAdapter :
    ListAdapter<Playlist, ItemPlaylistHorizontalViewHolder>(
        PlaylistDiffUtil(),
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemPlaylistHorizontalViewHolder {
        val binding = ItemPlaylistHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemPlaylistHorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemPlaylistHorizontalViewHolder,
        position: Int,
    ) {
        val item = getItem(position)
        holder.bindData(item)
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
    ): Boolean = oldItem == newItem
}
