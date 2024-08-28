package com.demo.home.recently

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.Songs
import com.demo.databinding.ItemSongRecentlyBinding

class ItemSongRecentlyViewHolder(
    val binding: ItemSongRecentlyBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(song: Any) {
        if (song is Songs) {
            binding.imgSong.setImageResource(R.drawable.img_artist_recently)
            binding.nameSong.text = song.name
        }
    }
}
