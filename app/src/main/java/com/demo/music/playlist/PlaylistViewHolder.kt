package com.demo.music.playlist

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.Playlist
import com.demo.databinding.ItemPlaylistBinding

/*class PlaylistViewHolder(
    val binding: ItemPlaylistBinding,
    val onPlaylistClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onPlaylistClick(position)
            }
        }
    }

    fun bind(playlist: Albums) {
        binding.tvAlbumName.text = playlist.albumName
        binding.tvYear.text = playlist.year
    }
}*/

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
        binding.artistName.text = playlist.songs.nameArtist
        binding.date.text = playlist.date
        binding.time.text = playlist.time
    }
}
