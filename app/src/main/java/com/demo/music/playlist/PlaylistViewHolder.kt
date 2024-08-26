package com.demo.music.playlist

import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemPlaylistBinding

class PlaylistViewHolder(
    val binding: ItemPlaylistBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bind(playlist: Playlist) {
        binding.tvAlbumName.text = playlist.albumName
        binding.tvYear.text = playlist.year
    }
}
