package com.demo.screen.detailsong.adapter

import androidx.recyclerview.widget.RecyclerView
import com.demo.R
import com.demo.data.model.Songs
import com.demo.databinding.ItemDetailSongBinding

class DetailSongViewHolder(
    val binding: ItemDetailSongBinding,
) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bindData(songs: Any) {
        if (songs is Songs) {
            binding.imgSong.setImageResource(songs.imgSong)
            binding.songName.text = songs.songName
            binding.artist.text = songs.artist
            binding.playPause.setImageResource(if (songs.isPlaying) R.drawable.ic_pause_circle else R.drawable.ic_play_circle)
        }
    }
}
