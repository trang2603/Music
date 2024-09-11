package com.demo.screen.playlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Playlist
import com.demo.databinding.ItemPlaylistBinding

class PlaylistViewHolder(
    val binding: ItemPlaylistBinding,
    val onPlaylistClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onPlaylistClick.invoke(position)
            }
        }
    }

    fun bindData(playlist: Playlist) {
        binding.img.setImageResource(R.drawable.ic_launcher_foreground)
        binding.artistName.text = playlist.songs.artist
        binding.date.text = playlist.date
        binding.time.text = playlist.time
    }
}
