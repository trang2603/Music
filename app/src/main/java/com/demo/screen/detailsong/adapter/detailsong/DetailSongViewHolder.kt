package com.demo.screen.detailsong.adapter.detailsong

import androidx.recyclerview.widget.RecyclerView
import com.demo.data.model.Songs
import com.demo.databinding.ItemDetailSongBinding

class DetailSongViewHolder(val binding: ItemDetailSongBinding): RecyclerView.ViewHolder(binding.root)  {
    init {

    }
    fun bindData(songs: Songs) {
        binding.imgSong.setImageResource(songs.imgSong)
        binding.songName.text = songs.songName
        binding.artist.text = songs.artist
    }
}