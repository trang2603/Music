package com.demo.screen.detailsong.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Playlist
import com.demo.databinding.ItemHeaderDetailsongBinding

class HeaderDetailSongViewHolder(
    val binding: ItemHeaderDetailsongBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(song: Any) {
        if (song is Playlist) {
            binding.songName.text = song.namePlaylist
        }
    }
}
