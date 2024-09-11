package com.demo.screen.home.adapter.showfavourite

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Songs
import com.demo.databinding.ItemSongFavouriteBinding

class ItemSongViewHolder(
    val binding: ItemSongFavouriteBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(song: Any) {
        if (song is Songs) {
            binding.img.setImageResource(R.drawable.img_song)
            binding.name.text = song.songName
        }
    }
}
