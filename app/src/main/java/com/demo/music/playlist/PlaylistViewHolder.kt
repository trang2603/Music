package com.demo.music.playlist

import androidx.recyclerview.widget.RecyclerView
import com.demo.databinding.ItemPlaylistBinding

class PlaylistViewHolder(
    val binding: ItemPlaylistBinding,
    val onPlaylistClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                onPlaylistClick(position)
            }
        }
    }

    fun bind(playlist: Playlist) {
        binding.tvAlbumName.text = playlist.albumName
        binding.tvYear.text = playlist.year
    }
}
