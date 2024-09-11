package com.demo.screen.home.adapter.playlitvertical

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Playlist
import com.demo.data.modelui.DataUi
import com.demo.databinding.PlaylistDetailVerticalBinding

class PlaylistVerticalViewHolder(
    val binding: PlaylistDetailVerticalBinding,
    val onLongClickPlaylist: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnLongClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onLongClickPlaylist.invoke(position)
            }
            true
        }
    }

    fun bindData(data: DataUi) {
        binding.nameSong.text = (data.data as Playlist).songs.songName
        binding.nameArtist.text = (data.data as Playlist).songs.artist
        binding.namePlaylist.text = (data.data as Playlist).namePlaylist
    }
}
