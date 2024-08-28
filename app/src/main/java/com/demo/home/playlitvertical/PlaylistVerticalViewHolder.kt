package com.demo.home.playlitvertical

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.Playlist
import com.demo.databinding.PlaylistDetailVerticalBinding

class PlaylistVerticalViewHolder(val binding: PlaylistDetailVerticalBinding): RecyclerView.ViewHolder(binding.root) {
    init {

    }
    fun bindData(playlist: Playlist) {
        binding.nameSong.text = playlist.songs.name
        binding.nameArtist.text = playlist.songs.nameArtist
        binding.namePlaylist.text = playlist.namePlaylist
    }
}