package com.demo.home.playlitvertical

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.DataUi
import com.demo.data.Playlist
import com.demo.databinding.PlaylistDetailVerticalBinding

class PlaylistVerticalViewHolder(
    val binding: PlaylistDetailVerticalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(data: DataUi) {
        binding.nameSong.text = (data.data as Playlist).songs.name
        binding.nameArtist.text = (data.data as Playlist).songs.nameArtist
        binding.namePlaylist.text = (data.data as Playlist).namePlaylist
    }
}
