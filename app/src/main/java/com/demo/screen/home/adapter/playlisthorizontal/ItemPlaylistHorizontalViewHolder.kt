package com.demo.screen.home.adapter.playlisthorizontal

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Playlist
import com.demo.databinding.ItemPlaylistHomeBinding

class ItemPlaylistHorizontalViewHolder(
    val binding: ItemPlaylistHomeBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(playlist: Playlist) {
        binding.img.setImageResource(R.drawable.img_playlist_home)
        binding.name.text = playlist.namePlaylist
    }

    fun bindData(
        payloads: MutableList<Any>,
        position: Int,
    ) {
    }
}
