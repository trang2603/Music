package com.demo.screen.home.adapter.recently

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Songs
import com.demo.databinding.ItemSongRecentlyBinding

class ItemSongRecentlyViewHolder(
    val binding: ItemSongRecentlyBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(song: Any) {
        if (song is Songs) {
            binding.imgSong.setImageResource(R.drawable.img_song)
            binding.nameSong.text = song.songName
        }
    }
}
